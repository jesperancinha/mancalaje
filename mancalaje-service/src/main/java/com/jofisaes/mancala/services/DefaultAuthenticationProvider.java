package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    public DefaultAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        if (authentication.getName() == null || authentication.getCredentials() == null) {
            return null;
        }

        if (authentication.getName().isEmpty() || authentication.getCredentials().toString().isEmpty()) {
            return null;
        }

        final Optional<User> appUser = this.userRepository.findById(authentication.getName());

        if (appUser.isPresent()) {
            final User user = appUser.get();
            final String providedUserEmail = authentication.getName();
            final Object providedUserPassword = authentication.getCredentials();

            if (providedUserEmail.equalsIgnoreCase(user.getEmail())
                    && providedUserPassword.equals(user.getPassword())) {

                return new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
            }
        }

        throw new UsernameNotFoundException("Invalid username/password.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
