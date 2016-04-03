package com.jfse.stonesgame.objects;

import java.util.Map;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public interface Board {


    void createPitsForPlayer(int nPits, int nInitialStones, Player player1, Player player2);

    int getnPits();

    Player getPlayer1();

    Player getPlayer2();

    Pit getTopLeftPiece();

    Map<String, Pit> getPitMap();
}
