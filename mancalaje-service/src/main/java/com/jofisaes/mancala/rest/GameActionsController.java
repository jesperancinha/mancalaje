package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.services.RoomsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_ACTIONS;

@RestController
@RequestMapping(MANCALA_ACTIONS)
public class GameActionsController extends AbstractUserController {

    @Autowired
    private RoomsManager roomsManager;

    @PutMapping(value = "nextMove/{holeId}")
    public BoardManager pressHoleId(
            @PathVariable("holeId") Integer holeId) {
        gameManagerService.swayStonesFromHole(userManagerService.getSessionUser(), holeId);
        BoardManager boardManager = userManagerService.getSessionUser().getBoardManager();
        boardManager.getBoard().getPlayer1().setBoardManager(boardManager);
        boardManager.getBoard().getPlayer2().setBoardManager(boardManager);
        roomsManager.getBoardManagerMap().put(boardManager.getBoardManagerId(), boardManager);
        return boardManager;
    }
}
