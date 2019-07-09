package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.game.BoardManagerDto;
import com.jofisaes.mancala.services.RoomsManager;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_BOARDS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController extends AbstractUserController implements Serializable {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BoardManagerDto createGame(@RequestBody Map<String, Object> payload) {
        return BoardManagerDto.builder().boardManager(gameManagerService.createBoard(userManagerService.getSessionUser(), payload.get("boardName").toString())).loggedPlayer(userManagerService.getSessionUser()).build();
    }

    @GetMapping(value = "{roomId}", produces = APPLICATION_JSON_VALUE)
    public BoardManagerDto getGame(
            @PathVariable("roomId") Long roomId) {
        Player sessionUser = this.userManagerService.getSessionUser();
        BoardManager boardManagerByRoomnId = gameManagerService.getBoardManagerByRoomnId(roomId);
        userManagerService.setSessionUser(boardManagerByRoomnId.refreshSessionUser(sessionUser));
        return BoardManagerDto.builder().boardManager(boardManagerByRoomnId).loggedPlayer(sessionUser).build();
    }

    @DeleteMapping(value = "{roomId}")
    public BoardManager removeRoom(
            @PathVariable("roomId") Long roomId) {
        return gameManagerService.removeRoom(roomId, userManagerService.getSessionUser());
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public BoardManager listCurrentGame() {
        return gameManagerService.listPlayerGame(userManagerService.getSessionUser());
    }

    @GetMapping(value = "all", produces = APPLICATION_JSON_VALUE)
    public RoomsManager listAllCurrentGames() {
        return gameManagerService.listAllGames();
    }


    @PutMapping(value = "nextMove/{holeId}")
    public BoardManager pressHoleId(
            @PathVariable("holeId") Integer holeId) {
        gameManagerService.swayStonesFromHole(userManagerService.getSessionUser(), holeId);
        return userManagerService.getSessionUser().getBoardManager();
    }

}
