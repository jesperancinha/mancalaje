package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_USERS;

@RestController(MANCALA_USERS)
public class UsersController {

    @Autowired
    private UserManagerService userManagerService;

    //TODO: After the static version is working
    @GetMapping(value = "create/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createGame(@PathVariable("username") String username) {
        userManagerService.getSessionUser().setName(username);
    }

}
