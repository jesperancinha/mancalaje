package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findById(email);
        return user.orElseThrow();
    }
}
