package com.jofisaes.mancala.services.user;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.jofisaes.mancala.entities.RoleType.ROLE_USER;

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

    public void saveUser(User user) {
        user.setDate(new Date(new java.util.Date().getTime()));
        user.setRole(ROLE_USER.name());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void remove(User user) {
        userRepository.deleteById(user.getEmail());
    }
}
