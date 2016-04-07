package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.Pit;
import com.jfse.stonesgame.objects.Player;
import com.jfse.stonesgame.objects.PlayerImpl;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
public class BoardManagerTest {

    final static Integer TEST_PITS = 6;

    final static Integer TEST_STONES = 8;

    @Test
    public void moveStonesPlayer1Stone0() throws Exception {
        final Player mockPlayer1 = new PlayerImpl(1, "Player One", "SESSIONID1");
        final Player mockPlayer2 = new PlayerImpl(2, "Player Two", "SESSIONID2");

        final BoardManager boardManager = new BoardManagerImpl(TEST_PITS, TEST_STONES, mockPlayer1, mockPlayer2);

        boardManager.moveStones("player10");

        Pit currentPit = boardManager.getBoard().getPlayerOnePiece();

        Stack<Integer> expectedStoneNumbers = new Stack<>();

        expectedStoneNumbers.push(0);
        expectedStoneNumbers.push(8);
        expectedStoneNumbers.push(8);
        expectedStoneNumbers.push(8);
        expectedStoneNumbers.push(8);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(1);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(0);

        for (int i = 0; i < TEST_PITS + 2; i++) {
            assertEquals(expectedStoneNumbers.pop(), currentPit.getnStones());
            currentPit = currentPit.getNextPit();
        }
    }

    @Test
    public void moveStonesPlayer1Stone5() throws Exception {
        final Player mockPlayer1 = new PlayerImpl(1, "Player One", "SESSIONID1");
        final Player mockPlayer2 = new PlayerImpl(2, "Player Two", "SESSIONID2");

        final BoardManager boardManager = new BoardManagerImpl(TEST_PITS, TEST_STONES, mockPlayer1, mockPlayer2);

        boardManager.moveStones("player15");

        Pit currentPit = boardManager.getBoard().getPlayerOnePiece();

        Stack<Integer> expectedStoneNumbers = new Stack<>();

        expectedStoneNumbers.push(0);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(9);
        expectedStoneNumbers.push(1);
        expectedStoneNumbers.push(0);
        expectedStoneNumbers.push(8);
        expectedStoneNumbers.push(8);
        expectedStoneNumbers.push(8);
        expectedStoneNumbers.push(8);
        expectedStoneNumbers.push(18);

        for (int i = 0; i < TEST_PITS + 2; i++) {
            assertEquals(expectedStoneNumbers.pop(), currentPit.getnStones());
            currentPit = currentPit.getNextPit();
        }
    }
}