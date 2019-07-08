package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class DefaultUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DefaultUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<User> userEntity = userRepository.findById(username);

        if (userEntity.isPresent()) {
            final User user = userEntity.get();
            return createUserDetails(user);
        }

        return null;
    }

    private org.springframework.security.core.userdetails.User createUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                "{noop}" + user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }

}