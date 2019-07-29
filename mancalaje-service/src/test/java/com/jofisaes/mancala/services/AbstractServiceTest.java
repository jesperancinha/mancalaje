package com.jofisaes.mancala.services;

import com.jofisaes.mancala.repository.UserRepository;
import com.jofisaes.mancala.services.authentication.DefaultUserDetailsService;
import com.jofisaes.mancala.services.game.GameManagerService;
import com.jofisaes.mancala.services.room.RoomsManagerService;
import com.jofisaes.mancala.services.user.UserManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AbstractServiceTest {

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
}
