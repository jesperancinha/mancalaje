package com.jofisaes.mancala.rest;

import com.google.common.annotations.VisibleForTesting;
import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.services.game.GameManagerService;
import com.jofisaes.mancala.services.room.RoomsManager;
import com.jofisaes.mancala.services.user.UserManagerService;
import com.jofisaes.mancala.services.user.UserService;
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

    @VisibleForTesting
    void updateBoardManager(BoardManager boardManager) {
        roomsManager.getBoardManagerMap().put(boardManager.getBoardManagerId(), boardManager);
    }
}
