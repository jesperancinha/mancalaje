package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.data.BoardManagerDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jofisaes.mancala.rest.mappings.MappingsUtils.MANCALA_BOARDS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping(MANCALA_BOARDS)
public class BoardsController extends AbstractUserController implements Serializable {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public BoardManagerDto createGame(@RequestBody Map<String, Object> payload) {
        final Player sessionUser = userManagerService.getSessionUser();
        return toDto(sessionUser, gameManagerService.createBoard(sessionUser, payload.get("boardName").toString()));
    }

    @GetMapping(value = "{roomId}", produces = APPLICATION_JSON_VALUE)
    public BoardManagerDto getGame(
            @PathVariable("roomId") Long roomId) {
        final Player sessionUser = this.userManagerService.getSessionUser();
        final BoardManager boardManager = this.gameManagerService.handleBoardManager(roomId);
        userManagerService.setSessionUser(boardManager.refreshSessionUser(sessionUser));
        updateBoardManager(boardManager);
        return toDto(sessionUser, boardManager);
    }

    @DeleteMapping(value = "{roomId}")
    public BoardManagerDto removeRoom(
            @PathVariable("roomId") Long roomId) {
        final Player sessionUser = userManagerService.getSessionUser();
        final BoardManager boardManager = gameManagerService.removeRoom(roomId, sessionUser);
        return toDto(sessionUser, boardManager);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public BoardManagerDto listCurrentGame() {
        final Player sessionUser = userManagerService.getSessionUser();
        final BoardManager boardManager = gameManagerService.listPlayerGame(sessionUser);
        return toDto(sessionUser, boardManager);
    }

    @GetMapping(value = "all", produces = APPLICATION_JSON_VALUE)
    public List<BoardManagerDto> listAllCurrentGames() {
        final Player sessionUser = userManagerService.getSessionUser();
        return gameManagerService.listAllGames()
                .parallelStream()
                .map(boardManager -> toDto(sessionUser, boardManager))
                .collect(Collectors.toList());
    }

    private BoardManagerDto toDto(Player sessionUser, BoardManager boardManager) {
        return BoardManagerDto.builder().boardManager(boardManager).loggedPlayer(sessionUser).build();
    }
}
