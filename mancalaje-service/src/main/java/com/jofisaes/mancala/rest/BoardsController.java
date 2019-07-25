package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.game.BoardManagerDto;
import com.jofisaes.mancala.services.room.RoomsManagerService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_BOARDS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController extends AbstractUserController implements Serializable {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BoardManagerDto createGame(@RequestBody Map<String, Object> payload) {
        final Player sessionUser = userManagerService.getSessionUser();
        return BoardManagerDto.builder()
                .boardManager(gameManagerService.createBoard(sessionUser, payload.get("boardName").toString())).loggedPlayer(sessionUser).build();
    }

    @GetMapping(value = "{roomId}", produces = APPLICATION_JSON_VALUE)
    public BoardManagerDto getGame(
            @PathVariable("roomId") Long roomId) {
        final Player sessionUser = this.userManagerService.getSessionUser();
        BoardManager boardManager = this.gameManagerService.handleBoardManager(roomId);
        userManagerService.setSessionUser(boardManager.refreshSessionUser(sessionUser));
        updateBoardManager(boardManager);
        return BoardManagerDto.builder().boardManager(boardManager).loggedPlayer(sessionUser).build();
    }

    @DeleteMapping(value = "{roomId}")
    public BoardManagerDto removeRoom(
            @PathVariable("roomId") Long roomId) {
        Player sessionUser = userManagerService.getSessionUser();
        BoardManager boardManager = gameManagerService.removeRoom(roomId, sessionUser);
        return BoardManagerDto.builder().boardManager(boardManager).loggedPlayer(sessionUser).build();
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
