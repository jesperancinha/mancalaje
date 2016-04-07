package com.steelzack.mancalaje.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class BoardImpl implements Board {
    public final static String PLAYER1 = "player1";
    public final static String PLAYER2 = "player2";

    private final int nPits;
    private final int nInitialStones;
    private final Player player1;
    private final Player player2;
    private final Map<String, Pit> pitMap;
    private Pit playerOnePiece;

    public BoardImpl(int nPits, int nInitialStones, Player player1, Player player2) {

        this.nPits = nPits;
        this.nInitialStones = nInitialStones;
        this.player1 = player1;
        this.player2 = player2;
        this.pitMap = new HashMap<>();

        createPitsForPlayer(nPits, nInitialStones, player1, player2);
    }

    @Override
    public void createPitsForPlayer(int nPits, int nInitialStones, Player player1, Player player2) {
        Pit previous1 = null;
        Pit previous2 = null;
        Pit lastPlayerPit1 = null;
        Pit lastPlayerPit2 = null;
        final BigPitImpl nextBigPit1 = new BigPitImpl(0, player1, PLAYER1);
        final BigPitImpl nextBigPit2 = new BigPitImpl(0, player2, PLAYER2);
        pitMap.put(PLAYER1, nextBigPit1);
        pitMap.put(PLAYER2, nextBigPit2);
        player1.setPlayerBigPit(nextBigPit1);
        player2.setPlayerBigPit(nextBigPit2);
        for (int i = 0; i < nPits; i++) {
            lastPlayerPit1 = new PitImpl(nInitialStones, player1, PLAYER1 + i);
            lastPlayerPit2 = new PitImpl(nInitialStones, player2, PLAYER2 + (nPits - i - 1));
            pitMap.put(lastPlayerPit1.getSharedKey(), lastPlayerPit1);
            pitMap.put(lastPlayerPit2.getSharedKey(), lastPlayerPit2);
            player1.addPit(lastPlayerPit1);
            player2.addPit(lastPlayerPit2);

            if (playerOnePiece == null) {
                playerOnePiece = lastPlayerPit1;
                nextBigPit2.setNextPit(playerOnePiece);
            }
            lastPlayerPit1.setOpositePit(lastPlayerPit2);
            lastPlayerPit2.setOpositePit(lastPlayerPit1);
            if (previous1 != null) {
                previous1.setNextPit(lastPlayerPit1);
            }
            if (previous2 != null) {
                lastPlayerPit2.setNextPit(previous2);
            } else {
                lastPlayerPit2.setNextPit(nextBigPit2);
            }
            previous1 = lastPlayerPit1;
            previous2 = lastPlayerPit2;

        }
        lastPlayerPit1.setNextPit(nextBigPit1);
        nextBigPit1.setNextPit(lastPlayerPit2);
        player1.invertPits();
        player2.orderPits();
    }


    @Override
    public int getnPits() {
        return nPits;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    public Pit getPlayerOnePiece() {
        return playerOnePiece;
    }

    @Override
    public Map<String, Pit> getPitMap() {
        return pitMap;
    }
}
