package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.game.RoomsManager;
import com.jofisaes.mancala.services.GameManagerService;
import com.jofisaes.mancala.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_BOARDS;

@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController implements Serializable {

    @Autowired
    private GameManagerService gameManagerService;

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping(value = "create/{boardName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createGame(@PathVariable("boardName") String boardName) {
        Player sessionUser = userManagerService.getSessionUser();
        if(StringUtils.isEmpty(sessionUser.getName())){
            sessionUser.setName("Player One");
        }
        gameManagerService.createBoard(sessionUser, boardName);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public BoardManager listCurrentGame() {
        return gameManagerService.listPlayerGame(userManagerService.getSessionUser());
    }

    @GetMapping(value = "listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoomsManager listAllCurrentGames() {
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
