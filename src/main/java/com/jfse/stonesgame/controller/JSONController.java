package com.jfse.stonesgame.controller;

import com.jfse.stonesgame.manager.BoardManager;
import com.jfse.stonesgame.model.BoardModel;
import com.jfse.stonesgame.model.Username;
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

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */

@Controller
@RequestMapping("/board")
public class JSONController {

    @Autowired
    private BoardManager boardManager;


    @Bean(name = "sessionRegistry")
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Autowired
    private SessionRegistry sessionRegistry;

    public List<SessionInformation> getActiveSessions() {
        List<SessionInformation> activeSessions = new ArrayList<>();
        for(Object principal : sessionRegistry.getAllPrincipals()) {
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

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody String addTitle(@ModelAttribute(value = "username") Username userInfo, BindingResult result)
            throws URISyntaxException {
        sessionRegistry.registerNewSession( userInfo.getUsername(), "principal");
        return "{}";
    }

    @RequestMapping(value = "startAgain", method = RequestMethod.GET)
    public
    @ResponseBody
    BoardModel startAgain() {
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
        final Player player1 = boardManager.getBoard().getPlayer1();
        final Player player2 = boardManager.getBoard().getPlayer2();
        final String valid = boardManager.moveStones(pitIdentifier);
        return  new BoardModel(player1.getPlayerBigPit(), //
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

    protected void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }
}
