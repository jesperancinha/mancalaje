package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.game.BoardManagerDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_ACTIONS;

@RestController
@RequestMapping(MANCALA_ACTIONS)
public class GameActionsController extends AbstractUserController {

    @PutMapping(value = "nextMove/{holeId}")
    public BoardManagerDto pressHoleId(
            @PathVariable("holeId") Integer holeId) throws InterruptedException {
        Player sessionUser = userManagerService.getSessionUser();
        gameManagerService.swayStonesFromHole(sessionUser, holeId);
        BoardManager boardManager = sessionUser.getBoardManager();
        boardManager.getBoard().getPlayer1().setBoardManager(boardManager);
        boardManager.getBoard().getPlayer2().setBoardManager(boardManager);
        updateBoardManager(boardManager);
        userService.refreshUser(sessionUser.getEmail());
        return BoardManagerDto.builder().boardManager(boardManager).loggedPlayer(sessionUser).build();
    }
}
