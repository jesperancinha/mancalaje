package com.steelzack.mancalaje.objects;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class BoardImplTest {

    final static Mockery context = new Mockery();

    final static Integer TEST_PITS = 6;

    final static Integer TEST_STONES = 6;

    final static String PLAYER1 = "Player-1";

    final static String PLAYER2 = "Player-2";

    @Test
    public void createPitsForPlayer() throws Exception {

        final Player mockPlayer1 = context.mock(Player.class, PLAYER1);
        final Player mockPlayer2 = context.mock(Player.class, PLAYER2);

        context.checking(new Expectations() {
            {
                exactly(1).of(mockPlayer1).setPlayerBigPit(with(any(Pit.class)));
                exactly(6).of(mockPlayer1).addPit(with(any(Pit.class)));
                exactly(1).of(mockPlayer2).setPlayerBigPit(with(any(Pit.class)));
                exactly(6).of(mockPlayer2).addPit(with(any(Pit.class)));
                exactly(1).of(mockPlayer1).invertPits();
                exactly(1).of(mockPlayer2).orderPits();

            }
        });

        final Board board = new BoardImpl(TEST_PITS, TEST_STONES, mockPlayer1, mockPlayer2);

        final Pit firstPit = board.getPlayerOnePiece();

        Pit currentPit = firstPit;

        assertEquals(TEST_PITS, firstPit.getnStones());

        currentPit = testSmallPitSequence(mockPlayer2, currentPit, "player1", board.getPitMap());

        assertSame(board.getPitMap().get("player1"), currentPit);
        currentPit = testBigPit(currentPit);

        currentPit = testSmallPitSequence(mockPlayer1, currentPit, "player2", board.getPitMap());

        assertSame(board.getPitMap().get("player2"), currentPit);
        currentPit = testBigPit(currentPit);

        assertSame(firstPit,currentPit);
    }

    private Pit testBigPit(Pit currentPit) {
        assertNotNull(currentPit);
        assertTrue(currentPit instanceof BigPitImpl);
        assertEquals(0,currentPit.getnStones().intValue());
        currentPit = currentPit.getNextPit();
        return currentPit;
    }

    private Pit testSmallPitSequence(Player mockPlayer, Pit currentPit,String playerPrefix, Map<String, Pit> pitMap) {
        for (int i = 0; i < TEST_PITS; i++) {
            assertNotNull("Failed while testing pit with index " + i, currentPit);
            assertFalse("Failed while testing pit with index " + i, currentPit instanceof BigPitImpl);
            assertEquals("Failed while testing pit with index " + i, TEST_PITS, currentPit.getnStones());
            assertNotNull(currentPit.getOpositePit());
            assertEquals(mockPlayer,currentPit.getOpositePit().getPlayer());
            assertSame(pitMap.get(playerPrefix + i), currentPit);
            currentPit = currentPit.getNextPit();
        }
        return currentPit;
    }

    @Test
    public void getnPits() throws Exception {

    }

    @Test
    public void setnInitialStones() throws Exception {

    }

    @Test
    public void getPlayer1() throws Exception {

    }

    @Test
    public void getPlayer2() throws Exception {

    }

    @Test
    public void getTopLeftPiece() throws Exception {

    }

}
