package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.data.UserDto;
import com.jofisaes.mancala.model.User;
import com.jofisaes.mancala.services.mail.MancalaJeMailService;
import com.jofisaes.mancala.services.user.UserManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.jofisaes.mancala.rest.mappings.MappingsUtils.MANCALA_USERS;

@RestController
@RequestMapping(MANCALA_USERS)
public class UsersController {

    private final UserService userService;

    private final UserManagerService userManagerService;

    private final MancalaJeMailService mancalaJeMailService;

    public UsersController(UserService userService, UserManagerService userManagerService, MancalaJeMailService mancalaJeMailService) {
        this.userService = userService;
        this.userManagerService = userManagerService;
        this.mancalaJeMailService = mancalaJeMailService;
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.saveUser(userDto.toUser());
        mancalaJeMailService.sendRegistrationMail(userDto);
        return UserDto.builder().name(user.getName()).email(user.getEmail()).build();
    }

    @GetMapping
    public UserDto getCurrentUser() {
        final Player sessionUser = userManagerService.getSessionUser();
        return UserDto.builder().name(sessionUser.getName()).email(sessionUser.getEmail()).build();
    }
}
