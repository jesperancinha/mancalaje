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

        Pit firstPlayerPit = new PitImpl(nInitialStones);
    }


}
