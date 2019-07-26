package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.game.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
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

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserDto> userDtoJacksonTester;

    private ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    @Test
    public void createUser() throws Exception {

        final UserDto userDto = UserDto.builder()
                .name("João Esperancinha")
                .email("jofisaes@gmail.com")
                .password("admin")
                .role("USER_ROLE")
                .build();
        JsonContent<UserDto> userDtoContent = userDtoJacksonTester.write(userDto);

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
        final User user = userArgumentCaptor.getValue();
        assertThat(user).isNotNull();
        final String email = user.getEmail();
        assertThat(email).isNotNull();
        assertThat(email).isEqualTo(userDto.getEmail());
    }
}