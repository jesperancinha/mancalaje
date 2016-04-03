package com.jfse.stonesgame.controller;

import com.jfse.stonesgame.manager.BoardManager;
import com.jfse.stonesgame.model.BoardModel;
import com.jfse.stonesgame.objects.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */

@Controller
@RequestMapping("/board")
public class JSONController {

    @Autowired
    private BoardManager boardManager;

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
}
