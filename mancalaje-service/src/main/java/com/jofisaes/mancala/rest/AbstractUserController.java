package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.services.GameManagerService;
import com.jofisaes.mancala.services.RoomsManager;
import com.jofisaes.mancala.services.UserManagerService;
import com.jofisaes.mancala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractUserController {

    @Autowired
    protected UserManagerService userManagerService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected GameManagerService gameManagerService;

    @Autowired
    private RoomsManager roomsManager;

    void updateBoardManager(BoardManager boardManager) {
        roomsManager.getBoardManagerMap().put(boardManager.getBoardManagerId(), boardManager);
    }
}
