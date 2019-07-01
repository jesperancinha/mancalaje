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
import java.util.Map;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_BOARDS;

@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController implements Serializable {

    @Autowired
    private GameManagerService gameManagerService;

    @Autowired
    private UserManagerService userManagerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void createGame(@RequestBody Map<String, Object> payload) {
        Player sessionUser = userManagerService.getSessionUser();
        if (StringUtils.isEmpty(sessionUser.getName())) {
            sessionUser.setName("Player One");
        }
        gameManagerService.createBoard(sessionUser, payload.get("boardName").toString());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BoardManager listCurrentGame() {
        return gameManagerService.listPlayerGame(userManagerService.getSessionUser());
    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
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
