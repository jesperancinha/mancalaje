package org.jesperancinha.games.kalagameservice.controller;

import org.jesperancinha.games.kalagameservice.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class KalaGameRegistrationController {

    private final PlayerService playerService;

    public KalaGameRegistrationController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/")
    public void createPlayer(Principal principal) {
        playerService.createPlayer(principal.getName());
    }

    @GetMapping("/user")
    public String greetUser(Principal principal) {
        return String.format("Hello, %s", principal.getName());
    }
}
