package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.services.user.UserManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Aspect
@Service
public class RestAspect {

    private final UserManagerService userManagerService;

    private final UserService userService;

    public RestAspect(UserManagerService userManagerService, UserService userService) {
        this.userManagerService = userManagerService;
        this.userService = userService;
    }

    private void setUpPlayer(User principal) {
        Player sessionUser = userManagerService.getSessionUser();
        if (Objects.isNull(sessionUser)) {
            sessionUser = new Player();
            userManagerService.setSessionUser(sessionUser);
        }
        com.jofisaes.mancala.entities.User userByEmail = userService.getUserByEmail(principal.getUsername());
        sessionUser.setName(userByEmail.getName());
        sessionUser.setEmail(userByEmail.getEmail());
    }


    @Before("execution(* com.jofisaes.mancala.rest.BoardsController.*(..))")
    public void logBeforeAllBoardController() {
        this.setUpPlayer((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Before("execution(* com.jofisaes.mancala.rest.RoomController.*(..))")
    public void logBeforeAllRoomController() {
        this.setUpPlayer((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Before("execution(* com.jofisaes.mancala.rest.GameActionsController.*(..))")
    public void logBeforeAllGameActionController() {
        this.setUpPlayer((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
