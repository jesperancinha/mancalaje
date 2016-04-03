package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.Board;
import com.jfse.stonesgame.objects.BoardImpl;
import com.jfse.stonesgame.objects.Pit;
import com.jfse.stonesgame.objects.Player;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class BoardManagerImpl implements BoardManager {

    private final Board board;

    BoardManagerImpl(int nPits, int nInitialStones, Player player1, Player player2)
    {

        this.board = new BoardImpl(nPits, nInitialStones,player1,player2);
    }

    @Override
    public void moveStones(String chosenPitKey){
        final Pit emptiedPit = this.board.getPitMap().get(chosenPitKey);
        final Player oponent = emptiedPit.getOpositePit().getPlayer();
        final Pit oponentBigPit = oponent.getPlayerBigPit();
        int currentStones = emptiedPit.getnStones();
        emptiedPit.emptyPit();
        Pit currentPit = emptiedPit.getNextPit();
        while (currentStones-- >0)
        {
            currentPit.addOneStone();
            currentPit = currentPit.getMextPitNotThisOne(oponentBigPit);
        }
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
