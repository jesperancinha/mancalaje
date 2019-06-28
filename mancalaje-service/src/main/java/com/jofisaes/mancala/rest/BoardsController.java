package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.services.GameManagerService;
import com.jofisaes.mancala.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_BOARDS;

@SessionScope
@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController {

    @Autowired
    private GameManagerService gameManagerService;

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createGame() {
        Player sessionUser = userManagerService.getSessionUser();
        gameManagerService.createBoard(sessionUser);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BoardManager> listAllCurrentGames() {
        return gameManagerService.listAllGames();
    }

    @PutMapping(value = "join/{boardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BoardManager joinGame(
            @PathVariable("boardId") Long gameId) {
        return gameManagerService.joinPlayer(gameId, userManagerService.getSessionUser());
    }

    @PutMapping(value = "nextMove/{holeId}")
    public BoardManager pressHoleId(
            @PathVariable("holeId") Integer holeId) {
        gameManagerService.swayStonesFromHole(userManagerService.getSessionUser(), holeId);
        return userManagerService.getSessionUser().getBoardManager();
    }


}
