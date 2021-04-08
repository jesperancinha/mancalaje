package org.jesperancinha.games.kalagameservice.controller;

import org.jesperancinha.games.kalagameservice.dto.BoardDto;
import org.jesperancinha.games.kalagameservice.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class KalaGameController {

    private final GameService gameService;

    public KalaGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public BoardDto getCurrentBoard(Principal principal) {
        return null;
    }
}

