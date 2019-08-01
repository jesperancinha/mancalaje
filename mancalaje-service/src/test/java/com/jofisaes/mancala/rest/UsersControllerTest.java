package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.game.UserDto;
import com.jofisaes.mancala.services.mail.MancalaJeMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_USERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
@WithMockUser
@AutoConfigureJsonTesters
public class UsersControllerTest extends AbstractControllerTest {

    private static final String USER_TEST_EMAIL = "jofisaes@gmail.com";
    private static final String USER_TEST_NAME = "João Esperancinha";
    private static final String USER_TEST_PASSWORD = "admin";
    private static final String USER_TEST_ROLE = "USER_ROLE";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MancalaJeMailService mancalaJeMailService;

    @Autowired
    private JacksonTester<UserDto> userDtoJacksonTester;

    private ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    private ArgumentCaptor<UserDto> userDtoArgumentCaptor = ArgumentCaptor.forClass(UserDto.class);

    @Test
    public void createUser() throws Exception {

        final UserDto userDto = UserDto.builder()
                .name(USER_TEST_NAME)
                .email(USER_TEST_EMAIL)
                .password(USER_TEST_PASSWORD)
                .role(USER_TEST_ROLE)
                .build();
        when(userService.saveUser(any(User.class))).thenReturn(userDto.toUser());

        final JsonContent<UserDto> userDtoContent = userDtoJacksonTester.write(userDto);

        mvc.perform(post(MANCALA_USERS)
                .with(csrf())
                .content(userDtoContent.getJson())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verifyZeroInteractions(roomsManagerService);
        verifyZeroInteractions(userManagerService);
        verifyZeroInteractions(gameManagerService);

        verify(userService, only()).saveUser(userArgumentCaptor.capture());
        verify(mancalaJeMailService, only()).sendRegistrationMail(userDtoArgumentCaptor.capture());

        final User user = userArgumentCaptor.getValue();
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(USER_TEST_NAME);
        assertThat(user.getEmail()).isEqualTo(USER_TEST_EMAIL);
        assertThat(user.getPassword()).isEqualTo(USER_TEST_PASSWORD);
        assertThat(user.getRole()).isEqualTo(USER_TEST_ROLE);
        final UserDto sentUserDto = userDtoArgumentCaptor.getValue();
        assertThat(sentUserDto).isNotNull();
        assertThat(sentUserDto.getName()).isEqualTo(userDto.getName());
        assertThat(sentUserDto.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(sentUserDto.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(sentUserDto.getRole()).isEqualTo(userDto.getRole());
    }
}