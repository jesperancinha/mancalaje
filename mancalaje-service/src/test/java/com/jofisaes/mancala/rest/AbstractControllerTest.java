package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.repository.UserRepository;
import com.jofisaes.mancala.services.authentication.DefaultUserDetailsService;
import com.jofisaes.mancala.services.user.UserService;
import org.junit.After;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verifyZeroInteractions;

public class AbstractControllerTest {

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

    @After
    public void tearDown(){
        verifyZeroInteractions(passwordEncoder);
        verifyZeroInteractions(userDetailsService);
        verifyZeroInteractions(authenticationProvider);
        verifyZeroInteractions(passwordEncoder);
        verifyZeroInteractions(userRepository);
        verifyZeroInteractions(userService);
    }
}
