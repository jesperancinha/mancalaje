package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.Player;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
public class BoardManagerImplTest {
    @Test
    public void decideWinner_Tie() throws Exception {
        final BoardManager boardManager = new BoardManagerImpl();
        final Player player1 = boardManager.getBoard().getPlayer1();
        boardManager.collectStones(player1);
        final Player player2 = boardManager.getBoard().getPlayer2();
        boardManager.collectStones(player2);

        boardManager.decideWinner(player1, player2);

        assertNull(boardManager.getWinner());
    }

    @Test
    public void decideWinner_Player1() throws Exception {
        final BoardManager boardManager = new BoardManagerImpl();
        final Player player1 = boardManager.getBoard().getPlayer1();
        boardManager.collectStones(player1);
        final Player player2 = boardManager.getBoard().getPlayer2();
        boardManager.collectStones(player2);
        player1.getPlayerBigPit().addOneStone();

        boardManager.decideWinner(player1, player2);

        assertSame(player1, boardManager.getWinner());
    }

    @Test
    public void decideWinner_Player2() throws Exception {
        final BoardManager boardManager = new BoardManagerImpl();
        final Player player1 = boardManager.getBoard().getPlayer1();
        boardManager.collectStones(player1);
        final Player player2 = boardManager.getBoard().getPlayer2();
        boardManager.collectStones(player2);
        player2.getPlayerBigPit().addOneStone();

        boardManager.decideWinner(player1, player2);

        assertSame(player2, boardManager.getWinner());
    }

    @Test
    public void collectStones() throws Exception {
        final BoardManager boardManager = new BoardManagerImpl();
        final Player player1 = boardManager.getBoard().getPlayer1();
        boardManager.collectStones(player1);
        final Player player2 = boardManager.getBoard().getPlayer2();
        boardManager.collectStones(player2);

        Assert.assertEquals(36, player1.getPlayerBigPit().getnStones().intValue());
        Assert.assertEquals(36, player2.getPlayerBigPit().getnStones().intValue());
    }
}