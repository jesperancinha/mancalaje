package com.jofisaes.mancala.rest;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_BOARDS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.game.BoardManagerDto;
import com.jofisaes.mancala.services.room.RoomsManagerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController extends AbstractUserController implements Serializable {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BoardManagerDto createGame(@RequestBody Map<String, Object> payload) {
        return BoardManagerDto.builder()
            .boardManager(gameManagerService.createBoard(userManagerService.getSessionUser(), payload.get("boardName").toString())).loggedPlayer(userManagerService.getSessionUser()).build();
    }

    @GetMapping(value = "{roomId}", produces = APPLICATION_JSON_VALUE)
    public BoardManagerDto getGame(
        @PathVariable("roomId") Long roomId) {
        Player sessionUser = this.userManagerService.getSessionUser();
        BoardManager boardManager = this.gameManagerService.handleBoardManager(roomId);
        userManagerService.setSessionUser(boardManager.refreshSessionUser(sessionUser));
        updateBoardManager(boardManager);
        return BoardManagerDto.builder().boardManager(boardManager).loggedPlayer(sessionUser).build();
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
    public RoomsManagerService listAllCurrentGames() {
        return gameManagerService.listAllGames();
    }

}
