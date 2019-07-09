package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.game.BoardManagerDto;
import com.jofisaes.mancala.services.GameManagerService;
import com.jofisaes.mancala.services.RoomsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_BOARDS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController extends AbstractUserController implements Serializable {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BoardManagerDto createGame(@RequestBody Map<String, Object> payload, Principal principal) {
        Player sessionUser = setUpPlayer(principal);
        return BoardManagerDto.builder().boardManager(gameManagerService.createBoard(sessionUser, payload.get("boardName").toString())).loggedPlayer(sessionUser).build();
    }

    @GetMapping(value = "{roomId}", produces = APPLICATION_JSON_VALUE)
    public BoardManagerDto getGame(
            @PathVariable("roomId") Long roomId, Principal principal) {
        this.setUpPlayer(principal);
        return BoardManagerDto.builder().boardManager(gameManagerService.getBoardManagerByRoomnId(roomId)).loggedPlayer(this.userManagerService.getSessionUser()).build();
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
