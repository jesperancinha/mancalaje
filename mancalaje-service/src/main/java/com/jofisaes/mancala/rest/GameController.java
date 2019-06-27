package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.services.GameManagerService;
import com.jofisaes.mancala.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;

@SessionScope
@RestController()
@RequestMapping("mancala")
public class GameController {

    @Autowired
    private GameManagerService gameManagerService;

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping(value = "game/create/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createGame(@PathVariable("username") String username) {
        Player sessionUser = userManagerService.getSessionUser();
        sessionUser.setName(username);
        gameManagerService.createBoard(sessionUser);
    }

    @GetMapping(value = "game/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BoardManager> listAllCurrentGames() {
        return gameManagerService.listAllGames();
    }

    @PutMapping(value = "game/join/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BoardManager joinGame(
        @PathVariable("gameId") Long gameId) {
        return gameManagerService.joinPlayer(gameId, userManagerService.getSessionUser());
    }

    @PutMapping(value = "game/nextMove/{holeId}")
    public BoardManager pressHoleId(
        @PathVariable("holeId") Integer holeId) {
        gameManagerService.swayStonesFromHole(userManagerService.getSessionUser(), holeId);
        return userManagerService.getSessionUser().getBoardManager();
    }


}
