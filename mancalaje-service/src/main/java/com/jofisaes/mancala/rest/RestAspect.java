package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.services.admin.AdminService;
import com.jofisaes.mancala.services.user.UserManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Aspect
@Service
public class RestAspect {

    private final UserManagerService userManagerService;

    private final UserService userService;

    private final AdminService adminService;

    public RestAspect(UserManagerService userManagerService, UserService userService, AdminService adminService) {
        this.userManagerService = userManagerService;
        this.userService = userService;
        this.adminService = adminService;
    }

    private void setUpPlayer(OAuth2Authentication oAuth2Authentication) {
        User principal = (User) oAuth2Authentication.getPrincipal();
        Player sessionUser = userManagerService.getSessionUser();
        if (Objects.isNull(sessionUser)) {
            sessionUser = new Player();
            userManagerService.setSessionUser(sessionUser);
        }
        com.jofisaes.mancala.model.User userByEmail = userService.getUserByEmail(principal.getUsername());
        if (Objects.isNull(userByEmail)) {
            String tokenValue = ((OAuth2AuthenticationDetails) oAuth2Authentication.getDetails()).getTokenValue();
            adminService.revokeToken(tokenValue);
        } else {
            sessionUser.setName(userByEmail.getName());
            sessionUser.setEmail(userByEmail.getEmail());
        }
    }


    @Before("execution(* com.jofisaes.mancala.rest.BoardsController.*(..))")
    public void logBeforeAllBoardController() {
        this.setUpPlayer(getAuthentication());
    }

    @Before("execution(* com.jofisaes.mancala.rest.RoomController.*(..))")
    public void logBeforeAllRoomController() {
        this.setUpPlayer(getAuthentication());
    }

    @Before("execution(* com.jofisaes.mancala.rest.GameActionsController.*(..))")
    public void logBeforeAllGameActionController() {
        this.setUpPlayer(getAuthentication());
    }

    private OAuth2Authentication getAuthentication() {
        return (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
