package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.entities.User;
import com.jofisaes.mancala.services.GameManagerService;
import com.jofisaes.mancala.services.UserManagerService;
import com.jofisaes.mancala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public class AbstractUserController {

    @Autowired
    protected UserManagerService userManagerService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected GameManagerService gameManagerService;

    Player setUpPlayer(Principal principal) {
        Player sessionUser = userManagerService.getSessionUser();
        User userByEmail = userService.getUserByEmail(principal.getName());
        sessionUser.setName(userByEmail.getName());
        sessionUser.setEmail(userByEmail.getEmail());
        return sessionUser;
    }
}
