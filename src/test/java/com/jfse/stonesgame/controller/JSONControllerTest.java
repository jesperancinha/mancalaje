package com.jfse.stonesgame.controller;

import com.jfse.stonesgame.manager.BoardManager;
import com.jfse.stonesgame.model.BoardModel;
import com.jfse.stonesgame.model.PitModel;
import com.jfse.stonesgame.objects.Pit;
import com.jfse.stonesgame.objects.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/mvc-dispatcher-servlet.xml"})
public class JSONControllerTest {

    @Autowired
    private BoardManager boardManager;

    @Test
    public void startAgain() throws Exception {
        boardManager.startBoard();
        final JSONController jsonController = new JSONController();
        jsonController.setBoardEnterpriseImpl(boardManager);
        final Pit playerBigPit1 = boardManager.getBoard().getPlayer1().getPlayerBigPit();
        playerBigPit1.addStones(10);
        final Pit playerBigPit2 = boardManager.getBoard().getPlayer2().getPlayerBigPit();
        playerBigPit2.addStones(10);

        assertEquals(10, playerBigPit1.getnStones().intValue());
        assertEquals(10, playerBigPit2.getnStones().intValue());

        jsonController.startAgain();

        assertEquals(0, boardManager.getBoard().getPlayer1().getPlayerBigPit().getnStones().intValue());
        assertEquals(0, boardManager.getBoard().getPlayer2().getPlayerBigPit().getnStones().intValue());
    }

    @Test
    public void getBoard() throws Exception {
        boardManager.startBoard();
        final JSONController jsonController = new JSONController();
        jsonController.setBoardEnterpriseImpl(boardManager);
        Player player1 = boardManager.getBoard().getPlayer1();

        BoardModel board = jsonController.getBoard();

        assertEquals("", board.getErrorMessage());
        assertEquals(player1.getPlayerName(), board.getCurrentPlayerName());
        assertEquals("player1",board.getLargePit1().getKeyName());
        assertEquals("player2",board.getLargePit2().getKeyName());
        assertNull(board.getWinnerPlayerName());
        assertFalse(board.isGameOver());

        assertStonesInPits(board.getPits1());
        assertStonesInPits(board.getPits2());
    }

    private void assertStonesInPits(List<PitModel> pits1) {
        for(PitModel p : pits1)
        {
            assertEquals(6,p.getnStones().intValue());
        }
    }

    @Test
    public void selectPit() throws Exception {
        final JSONController jsonController = new JSONController();
        jsonController.setBoardEnterpriseImpl(boardManager);
        final Player player1 = boardManager.getBoard().getPlayer1();
        final Player player2 = boardManager.getBoard().getPlayer2();

        BoardModel board = jsonController.selectPit("player10");

        assertEquals("", board.getErrorMessage());
        assertEquals(player1.getPlayerName(), board.getCurrentPlayerName());
        assertEquals("player1",board.getLargePit1().getKeyName());
        assertEquals("player2",board.getLargePit2().getKeyName());
        assertNull(board.getWinnerPlayerName());
        assertFalse(board.isGameOver());

        assertEquals(7, board.getPits1().get(0).getnStones().intValue());
        assertEquals(7, board.getPits1().get(2).getnStones().intValue());
        assertEquals(7, board.getPits1().get(3).getnStones().intValue());
        assertEquals(7, board.getPits1().get(4).getnStones().intValue());
        assertEquals(0, board.getPits1().get(5).getnStones().intValue());

        assertEquals(6, board.getPits2().get(0).getnStones().intValue());
        assertEquals(6, board.getPits2().get(2).getnStones().intValue());
        assertEquals(6, board.getPits2().get(3).getnStones().intValue());
        assertEquals(6, board.getPits2().get(4).getnStones().intValue());
        assertEquals(6, board.getPits2().get(5).getnStones().intValue());

        assertEquals(1, player1.getPlayerBigPit().getnStones().intValue());
        assertEquals(0, player2.getPlayerBigPit().getnStones().intValue());
    }
}