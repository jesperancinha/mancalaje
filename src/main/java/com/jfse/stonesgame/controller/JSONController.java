package com.jfse.stonesgame.controller;

import com.jfse.stonesgame.manager.*;
import com.jfse.stonesgame.model.*;
import com.jfse.stonesgame.model.ResponseStatus;
import com.jfse.stonesgame.objects.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofi    lipesabinoesperancinha on 02-04-16.
 */

@Controller
@RequestMapping("/board")
public class JSONController {

    @Autowired
    private BoardEnterprise boardEnterpriseImpl;

    /**
     * Session based user
     */
    @Autowired
    private Username currentUser;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private SessionListKeeper sessionListKeeper;

    @Bean(name = "sessionRegistry")
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean(name = "sessionListKeeper")
    public SessionListKeeper sessionListKeeper()
    {
        return new SessionListKeeperImpl();
    }


    private List<SessionInformation> getActiveSessions() {
        List<SessionInformation> activeSessions = new ArrayList<>();
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            activeSessions.addAll(sessionRegistry.getAllSessions(principal, false));
        }
        return activeSessions;
    }

    public void logoutSession(String sessionId) {
        SessionInformation session = sessionRegistry.getSessionInformation(sessionId);
        Object principalObj = session.getPrincipal();
        if (principalObj instanceof User) {
            User user = (User) principalObj;
        }

        if (session != null) {
            session.expireNow();
        }
    }


    @RequestMapping(value = "sessionlist", method = RequestMethod.GET)
    public
    @ResponseBody
    SessionList login(BindingResult result)
            throws URISyntaxException {
        sessionListKeeper.setSessionList(getActiveSessions());
        return new SessionList(sessionListKeeper.getSessionList());
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public
    @ResponseBody
    Response login(@ModelAttribute(value = "username") Username userInfo, BindingResult result)
            throws URISyntaxException {
        sessionRegistry.registerNewSession(getSessionId(), userInfo.getUsername());
        currentUser.setUsername(userInfo.getUsername());
        return new Response(ResponseStatus.OK);
    }

    @RequestMapping(value = "startAgain", method = RequestMethod.GET)
    public
    @ResponseBody
    BoardModel startAgain() {
        return startBoard();
    }

    @RequestMapping(value = "/stonesgame.htm", method = RequestMethod.GET)
    public String startStonesGame() {
        if (currentUser.getUsername() == null || //
                sessionRegistry.getSessionInformation(getSessionId()) == null) {
            return "login";
        } else {
            return "stonesgame";
        }
    }

    private String getSessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    private BoardModel startBoard() {
        final BoardManager boardManager= boardEnterpriseImpl.getBoardManagerByBoardID(getSessionId());
        boardManager.startBoard();
        final Player player1 = boardManager.getBoard().getPlayer1();
        final Player player2 = boardManager.getBoard().getPlayer2();
        return new BoardModel(player1.getPlayerBigPit(), //
                player2.getPlayerBigPit(), //
                player1.getOwnedPits(), //
                player2.getOwnedPits(), //
                boardManager.getCurrentPlayer().getPlayerName(), //
                boardManager.getCurrentPlayer().getPlayerId(), //
                "", //
                boardManager.isGameOver(), //
                boardManager.getWinner() == null ? null : boardManager.getWinner().getPlayerName()//
        );
    }

    @RequestMapping(value = "refreshBoard", method = RequestMethod.GET)
    public
    @ResponseBody
    BoardModel getBoard() {
        final BoardManager boardManager= boardEnterpriseImpl.getBoardManagerByBoardID(getSessionId());
        final Player player1 = boardManager.getBoard().getPlayer1();
        final Player player2 = boardManager.getBoard().getPlayer2();
        return new BoardModel(player1.getPlayerBigPit(), //
                player2.getPlayerBigPit(), //
                player1.getOwnedPits(), //
                player2.getOwnedPits(), //
                boardManager.getCurrentPlayer().getPlayerName(), //
                boardManager.getCurrentPlayer().getPlayerId(), //
                "", //
                boardManager.isGameOver(), //
                boardManager.getWinner() == null ? null : boardManager.getWinner().getPlayerName() //
        );
    }

    @RequestMapping(value = "selectPit/{pitIdentifier}", method = RequestMethod.GET)
    public
    @ResponseBody
    BoardModel selectPit(@PathVariable String pitIdentifier) {
        final BoardManager boardManager= boardEnterpriseImpl.getBoardManagerByBoardID(getSessionId());
        final Player player1 = boardManager.getBoard().getPlayer1();
        final Player player2 = boardManager.getBoard().getPlayer2();
        final String valid = boardManager.moveStones(pitIdentifier);
        return new BoardModel(player1.getPlayerBigPit(), //
                player2.getPlayerBigPit(), //
                player1.getOwnedPits(), //
                player2.getOwnedPits(), //
                boardManager.getCurrentPlayer().getPlayerName(), //
                boardManager.getCurrentPlayer().getPlayerId(), //
                valid,
                boardManager.isGameOver(),//
                boardManager.getWinner() == null ? null : boardManager.getWinner().getPlayerName() //
        );
    }


    /**
     * For tests only
     * @param boardEnterpriseImpl
     */
    protected void setBoardEnterpriseImpl(BoardEnterpriseImpl boardEnterpriseImpl) {
        this.boardEnterpriseImpl = boardEnterpriseImpl;
    }
}
