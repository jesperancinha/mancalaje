package org.jesperancinha.games.kalagameservice.configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@Primary
@Profile({ "prod", "local" })
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DefaultAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (authentication.getName() == null || authentication.getCredentials() == null) {
            return null;
        }
        if (authentication.getName()
            .isEmpty() || authentication.getCredentials()
            .toString()
            .isEmpty()) {
            return null;
        }
        final Optional<User> appUser = this.userRepository.findById(authentication.getName()).blockOptional();

        if (appUser.isPresent()) {
            final User user = appUser.get();
            final String providedUserEmail = authentication.getName();
            final String providedUserPassword = (String) authentication.getCredentials();
            if (providedUserEmail.equalsIgnoreCase(user.getEmail()) && passwordEncoder.matches(providedUserPassword, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
            }
        }

        throw new UsernameNotFoundException("Invalid username/password.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
