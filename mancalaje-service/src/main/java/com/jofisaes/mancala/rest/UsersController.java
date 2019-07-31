package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.game.UserDto;
import com.jofisaes.mancala.services.mail.MancalaJeMailService;
import com.jofisaes.mancala.services.user.UserManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_USERS;

@RestController()
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
    public void createUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto.toUser());
        mancalaJeMailService.sendEmail(userDto);
    }

    @GetMapping
    public UserDto getCurrentUser() {
        final Player sessionUser = userManagerService.getSessionUser();
        return UserDto.builder().name(sessionUser.getName()).email(sessionUser.getEmail()).build();
    }
}
