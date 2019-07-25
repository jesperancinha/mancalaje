package com.jofisaes.mancala.rest;

import com.google.common.annotations.VisibleForTesting;
import com.jofisaes.mancala.repository.UserRepository;
import com.jofisaes.mancala.services.authentication.DefaultUserDetailsService;
import com.jofisaes.mancala.services.game.GameManagerService;
import com.jofisaes.mancala.services.room.RoomsManagerService;
import com.jofisaes.mancala.services.user.UserManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.junit.After;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verifyZeroInteractions;

public class AbstractControllerTest {

    @VisibleForTesting
    static final String TEST_GAME_1 = "game1";

    @VisibleForTesting
    static final String TEST_FAKE_EMAIL = "fakeEmail";

    @MockBean
    protected UserService userService;

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected AuthenticationProvider authenticationProvider;

    @MockBean
    protected DefaultUserDetailsService userDetailsService;

    @MockBean
    protected PasswordEncoder passwordEncoder;

    @MockBean
    protected UserManagerService userManagerService;

    @MockBean
    protected GameManagerService gameManagerService;

    @MockBean
    protected RoomsManagerService roomsManagerService;

    @After
    public void tearDown() {
        verifyZeroInteractions(passwordEncoder);
        verifyZeroInteractions(userDetailsService);
        verifyZeroInteractions(authenticationProvider);
        verifyZeroInteractions(passwordEncoder);
        verifyZeroInteractions(userRepository);
    }
}
