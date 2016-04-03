package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.Board;
import com.jfse.stonesgame.objects.BoardImpl;
import com.jfse.stonesgame.objects.Player;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class BoardManager {

    private final Board board;

    BoardManager(int nPits, int nInitialStones, Player player1, Player player2)
    {

        this.board = new BoardImpl(nPits, nInitialStones,player1,player2);
    }
}
