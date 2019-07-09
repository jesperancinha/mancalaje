package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.game.BoardManagerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_ROOMS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    @ResponseStatus(value = HttpStatus.OK)
    public void leaveGame(
            @PathVariable("roomId") Long roomId) {
        final Player sessionUser = userManagerService.getSessionUser();
        final Player player = gameManagerService.leaveRoom(roomId, sessionUser);
        sessionUser.reset();
        userManagerService.setSessionUser(player);
    }

}
