package com.jofisaes.mancala.services.user;

import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.exception.TooManyUsersException;
import com.jofisaes.mancala.exception.UserAlreadyExistsException;
import com.jofisaes.mancala.exception.UserRemovedException;
import com.jofisaes.mancala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static com.jofisaes.mancala.entities.RoleType.ROLE_USER;

@Service
@Transactional
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private int maxUsers;


    public UserService(@Value("${mancalaje.max-users:100}") int maxUsers, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.maxUsers = maxUsers;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static Timestamp getCurrentSqlDateTime() {
        return new Timestamp(new java.util.Date().getTime());
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findById(email);
        return user.orElseThrow(UserRemovedException::new);
    }

    public User saveUser(User user) {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        int userCount = (int) userRepository.count();
        if (userCount >= maxUsers) {
            throw new TooManyUsersException(userCount);
        }
        user.setDate(getCurrentSqlDateTime());
        user.setRole(ROLE_USER.name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void remove(User user) {
        userRepository.deleteByEmail(user.getEmail());
    }

    public void refreshUser(String email) {
        Optional<User> optionalUser = userRepository.findById(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDate(getCurrentSqlDateTime());
            userRepository.save(user);
        }
    }
}
