package com.steelzack.mancalaje.controller;

import com.steelzack.mancalaje.manager.BoardEnterprise;
import com.steelzack.mancalaje.manager.BoardManager;
import com.steelzack.mancalaje.manager.BoardManagerImpl;
import com.steelzack.mancalaje.manager.SelectedUserKeep;
import com.steelzack.mancalaje.model.BoardModel;
import com.steelzack.mancalaje.model.PitModel;
import com.steelzack.mancalaje.objects.Pit;
import com.steelzack.mancalaje.objects.Player;
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
    private BoardEnterprise boardManagerService;

    @Test
    public void startAgain() throws Exception {
        final String playerOneName = "player1";
        final String sessionId1 = "SESSIONID1";
        final String playerTwoName = "player2";
        final String sessionId2 = "SESSIONID2";
        BoardManager boardManager = new BoardManagerImpl(playerOneName, sessionId1, playerTwoName, sessionId2);
        boardManager.startBoard(playerOneName, sessionId1, playerTwoName, sessionId2);
        final JSONController jsonController = new JSONController();
        boardManagerService.addBoardManager("SESSIONID", boardManager);
        jsonController.setBoardEnterpriseImpl(boardManagerService);
        final Pit playerBigPit1 = boardManager.getBoard().getPlayer1().getPlayerBigPit();
        playerBigPit1.addStones(10);
        final Pit playerBigPit2 = boardManager.getBoard().getPlayer2().getPlayerBigPit();
        playerBigPit2.addStones(10);

        assertEquals(10, playerBigPit1.getnStones().intValue());
        assertEquals(10, playerBigPit2.getnStones().intValue());

        jsonController.startAgain(new SelectedUserKeep(), null);

        assertEquals(0, boardManager.getBoard().getPlayer1().getPlayerBigPit().getnStones().intValue());
        assertEquals(0, boardManager.getBoard().getPlayer2().getPlayerBigPit().getnStones().intValue());
    }

    @Test
    public void getBoard() throws Exception {
        final String playerOneName = "player1";
        final String sessionId1 = "SESSIONID1";
        final String playerTwoName = "player2";
        final String sessionId2 = "SESSIONID2";
        BoardManager boardManager = new BoardManagerImpl(playerOneName, sessionId1, playerTwoName, sessionId2);
        boardManager.startBoard(playerOneName, sessionId1, playerTwoName, sessionId2);
        final JSONController jsonController = new JSONController();
        boardManagerService.addBoardManager("SESSIONID", boardManager);
        jsonController.setBoardEnterpriseImpl(boardManagerService);
        Player player1 = boardManager.getBoard().getPlayer1();

        BoardModel board = jsonController.getBoard();

        assertEquals("", board.getErrorMessage());
        assertEquals(player1.getPlayerName(), board.getCurrentPlayerName());
        assertEquals("player1", board.getLargePit1().getKeyName());
        assertEquals("player2", board.getLargePit2().getKeyName());
        assertNull(board.getWinnerPlayerName());
        assertFalse(board.isGameOver());

        assertStonesInPits(board.getPits1());
        assertStonesInPits(board.getPits2());
    }

    private void assertStonesInPits(List<PitModel> pits1) {
        for (PitModel p : pits1) {
            assertEquals(6, p.getnStones().intValue());
        }
    }

    @Test
    public void selectPit() throws Exception {
        final String playerOneName = "player1";
        final String sessionId1 = "SESSIONID1";
        final String playerTwoName = "player2";
        final String sessionId2 = "SESSIONID2";
        BoardManager boardManager = new BoardManagerImpl(playerOneName, sessionId1, playerTwoName, sessionId2);
        boardManager.startBoard(playerOneName, sessionId1, playerTwoName, sessionId2);
        final JSONController jsonController = new JSONController();
        boardManagerService.addBoardManager("SESSIONID", boardManager);
        jsonController.setBoardEnterpriseImpl(boardManagerService);
        final Player player1 = boardManager.getBoard().getPlayer1();
        final Player player2 = boardManager.getBoard().getPlayer2();

        BoardModel board = jsonController.selectPit("player10");

        assertEquals("", board.getErrorMessage());
        assertEquals(player1.getPlayerName(), board.getCurrentPlayerName());
        assertEquals("player1", board.getLargePit1().getKeyName());
        assertEquals("player2", board.getLargePit2().getKeyName());
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
