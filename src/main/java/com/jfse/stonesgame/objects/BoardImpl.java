package com.jfse.stonesgame.objects;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class BoardImpl implements Board {
    private int nPits;
    private int nInitialStones;
    private Player player1;
    private Player player2;
    private Pit topLeftPiece;

    public BoardImpl(int nPits, int nInitialStones, Player player1, Player player2) {

        this.nPits = nPits;
        this.nInitialStones = nInitialStones;
        this.player1 = player1;
        this.player2 = player2;

        createPitsForPlayer(nPits, nInitialStones, player1, player2);
    }

    @Override
    public void createPitsForPlayer(int nPits, int nInitialStones, Player player1, Player player2) {
        Pit previous1 = null;
        Pit previous2 = null;
        Pit lastPlayerPit1 = null;
        Pit lastPlayerPit2 = null;
        final BigPitImpl nextBigPit1 = new BigPitImpl(0, player2);
        final BigPitImpl nextBigPit2 = new BigPitImpl(0, player1);
        for (int i = 0; i< nPits; i++) {
            lastPlayerPit1 = new PitImpl(nInitialStones, player1);
            lastPlayerPit2 = new PitImpl(nInitialStones, player2);
            if(topLeftPiece == null)
            {
                topLeftPiece = lastPlayerPit1;
                nextBigPit2.setNextPit(topLeftPiece);
            }
            lastPlayerPit1.setOpositePit(lastPlayerPit2);
            lastPlayerPit2.setOpositePit(lastPlayerPit1);
            if(previous1 != null)
            {
                previous1.setNextPit(lastPlayerPit1);
            }
            if(previous2 !=  null)
            {
                lastPlayerPit2.setNextPit(previous2);
            }else
            {
                lastPlayerPit2.setNextPit(nextBigPit2);
            }
            previous1 = lastPlayerPit1;
            previous2 = lastPlayerPit2;

        }
        lastPlayerPit1.setNextPit(nextBigPit1);
        nextBigPit1.setNextPit(lastPlayerPit2);
    }


    @Override
    public int getnPits() {
        return nPits;
    }

    public void setnInitialStones(int nInitialStones) {
        this.nInitialStones = nInitialStones;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }

    @Override
    public Pit getTopLeftPiece() {
        return topLeftPiece;
    }
}
