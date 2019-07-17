package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.game.UserDto;
import com.jofisaes.mancala.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_USERS;

@RestController(MANCALA_USERS)
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGame(@RequestBody UserDto userDto) {
        userService.saveUser(userDto.toUser());
    }

}
