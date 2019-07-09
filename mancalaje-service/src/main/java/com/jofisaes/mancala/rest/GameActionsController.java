package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.game.BoardManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jofisaes.mancala.rest.Mappings.MANCALA_ACTIONS;

@RestController
@RequestMapping(MANCALA_ACTIONS)
public class GameActionsController extends AbstractUserController {

    @PutMapping(value = "nextMove/{holeId}")
    public BoardManager pressHoleId(
            @PathVariable("holeId") Integer holeId) {
        gameManagerService.swayStonesFromHole(userManagerService.getSessionUser(), holeId);
        return userManagerService.getSessionUser().getBoardManager();
    }
}
