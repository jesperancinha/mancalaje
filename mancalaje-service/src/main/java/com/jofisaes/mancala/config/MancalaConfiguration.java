package com.jofisaes.mancala.config;

import com.jofisaes.mancala.services.authentication.DefaultUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MancalaConfiguration {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private DefaultUserDetailsService userDetailsService;

    public MancalaConfiguration(DefaultUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return getPasswordEncoder();
    }

    private PasswordEncoder getPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }
}
