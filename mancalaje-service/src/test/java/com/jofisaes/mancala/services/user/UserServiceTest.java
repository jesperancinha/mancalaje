package com.jofisaes.mancala.services.user;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.exception.TooManyUsersException;
import com.jofisaes.mancala.exception.UserRemovedException;
import com.jofisaes.mancala.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.jofisaes.mancala.entities.RoleType.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String TEST_USER_EMAIL = "jofisaes@gmail.com";

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private UserService userService;

    @Before
    public void setUp() {
        this.userService = new UserService(20, userRepository, passwordEncoder);
    }

    @Test
    public void getUserByEmail() {
        final User user = new User();
        final Optional<User> optionalUser = Optional.of(user);
        user.setEmail(TEST_USER_EMAIL);
        when(userRepository.findById(TEST_USER_EMAIL)).thenReturn(optionalUser);

        User userByEmail = userService.getUserByEmail(TEST_USER_EMAIL);

        verify(userRepository, only()).findById(TEST_USER_EMAIL);
        assertThat(userByEmail.getEmail()).isEqualTo(TEST_USER_EMAIL);
        assertThat(userByEmail).isSameAs(user);
    }

    @Test
    public void getUserByEmail_userNotFound_exception() {
        when(userRepository.findById(TEST_USER_EMAIL)).thenReturn(Optional.empty());

        assertThatExceptionOfType(UserRemovedException.class).isThrownBy(() -> userService.getUserByEmail(TEST_USER_EMAIL));
    }

    @Test
    public void saveUser() {
        final long highestId = 10L;
        final User testUser = User.builder().email(TEST_USER_EMAIL).build();
        when(userRepository.count()).thenReturn(highestId);

        userService.saveUser(testUser);

        verify(userRepository, times(1)).count();
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
        final User argumentCaptorValue = userArgumentCaptor.getValue();
        assertThat(argumentCaptorValue.getRole()).isEqualTo(ROLE_USER.name());
        final Timestamp userDate = argumentCaptorValue.getDate();
        assertThat(userDate).isNotNull();
        final long currentTimeStamp = new Date().getTime();
        final long userTimeStamp = userDate.getTime();
        assertThat(Math.abs(userTimeStamp - currentTimeStamp)).isGreaterThan(TimeUnit.SECONDS.toMinutes(1));
    }

    @Test
    public void saveUserTooManyUsersExceptiom() {
        final long highestId = 20L;
        final User testUser = User.builder().email(TEST_USER_EMAIL).build();
        when(userRepository.count()).thenReturn(highestId);

        assertThatExceptionOfType(TooManyUsersException.class).isThrownBy(() -> userService.saveUser(testUser));
    }

    @Test
    public void getAllUsers() {
        final List<User> allUserList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(allUserList);

        final List<User> allUsersResult = userService.getAllUsers();

        verify(userRepository, only()).findAll();
        assertThat(allUserList).isSameAs(allUsersResult);
    }


    @Test
    public void remove() {
        User testUser = new User();
        testUser.setEmail(TEST_USER_EMAIL);
        doNothing().when(userRepository).deleteByEmail(TEST_USER_EMAIL);

        userService.remove(testUser);

        verify(userRepository, only()).deleteByEmail(TEST_USER_EMAIL);
    }

    @Test
    public void refreshUser() {
        User testUser = new User();
        testUser.setEmail(TEST_USER_EMAIL);
        Optional<User> optionalUser = Optional.of(testUser);
        when(userRepository.findById(TEST_USER_EMAIL)).thenReturn(optionalUser);
        when(userRepository.save(testUser)).thenReturn(testUser);

        userService.refreshUser(testUser.getEmail());

        verify(userRepository, times(1)).findById(TEST_USER_EMAIL);
        verify(userRepository, times(1)).save(testUser);
        assertThat(testUser.getDate()).isNotNull();
    }
}