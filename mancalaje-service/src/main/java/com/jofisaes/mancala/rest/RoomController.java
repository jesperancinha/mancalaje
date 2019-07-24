package com.jofisaes.mancala.rest;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_ROOMS;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.game.BoardManagerDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(MANCALA_ROOMS)
public class RoomController extends AbstractUserController {


    @PutMapping(value = "{roomId}", produces = APPLICATION_JSON_VALUE)
    public BoardManagerDto joinGame(
        @PathVariable("roomId") Long roomId) {
        BoardManager boardManager = gameManagerService.joinPlayer(roomId, userManagerService.getSessionUser());
        return BoardManagerDto.builder().boardManager(boardManager).loggedPlayer(this.userManagerService.getSessionUser()).build();
    }

    @DeleteMapping(value = "{roomId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = OK)
    public void leaveGame(
        @PathVariable("roomId") Long roomId) {
        final Player sessionUser = userManagerService.getSessionUser();
        final Player player = gameManagerService.leaveRoom(roomId, sessionUser.getEmail());
        sessionUser.reset();
        userManagerService.setSessionUser(player);
    }

    @DeleteMapping
    @ResponseStatus(value = OK)
    public void leaveAllGames() {
        final Player sessionUser = userManagerService.getSessionUser();
        gameManagerService.leaveAllRooms(sessionUser.getEmail());
    }

}
