package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.game.UserDto;
import com.jofisaes.mancala.services.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_USERS;

@RestController()
@RequestMapping(MANCALA_USERS)
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto.toUser());
    }

}
