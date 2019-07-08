package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.services.GameManagerService;
import com.jofisaes.mancala.services.RoomsManager;
import com.jofisaes.mancala.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_BOARDS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController implements Serializable {

    @Autowired
    private GameManagerService gameManagerService;

    @Autowired
    private UserManagerService userManagerService;

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BoardManager createGame(@RequestBody Map<String, Object> payload) {
        Player sessionUser = userManagerService.getSessionUser();
        if (StringUtils.isEmpty(sessionUser.getName())) {
            sessionUser.setName("Player One");
        }
        return gameManagerService.createBoard(sessionUser, payload.get("boardName").toString());
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public BoardManager listCurrentGame() {
        return gameManagerService.listPlayerGame(userManagerService.getSessionUser());
    }

    @GetMapping(value = "all", produces = APPLICATION_JSON_VALUE)
    public RoomsManager listAllCurrentGames() {
        return gameManagerService.listAllGames();
    }

    @PutMapping(value = "{roomId}", produces = APPLICATION_JSON_VALUE)
    public BoardManager joinGame(
            @PathVariable("roomId") Long roomId) {
        return gameManagerService.joinPlayer(roomId, userManagerService.getSessionUser());
    }

    @DeleteMapping(value = "{roomId}")
    public BoardManager removeRoom(
            @PathVariable("roomId") Long roomId) {
        return gameManagerService.removeRoom(roomId, userManagerService.getSessionUser());
    }

    @PutMapping(value = "nextMove/{holeId}")
    public BoardManager pressHoleId(
            @PathVariable("holeId") Integer holeId) {
        gameManagerService.swayStonesFromHole(userManagerService.getSessionUser(), holeId);
        return userManagerService.getSessionUser().getBoardManager();
    }


}
