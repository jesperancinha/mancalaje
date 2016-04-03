package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
@Service("boardManagerService")
@Component
public class BoardManagerImpl implements BoardManager {

    private  Board board;

    private Player currentPlayer;

    private boolean gameOver;

    BoardManagerImpl() {
        startBoard();
    }

    BoardManagerImpl(int nPits, int nInitialStones, Player player1, Player player2) {
        this.board = new BoardImpl(nPits, nInitialStones, player1, player2);
        currentPlayer = player1;
    }

    @Override
    public String moveStones(String chosenPitKey) {
        final Pit emptiedPit = this.board.getPitMap().get(chosenPitKey);
        if(emptiedPit.getPlayer() != currentPlayer){
            return "Invalid move!";
        }
        final Player oponent = emptiedPit.getOpositePit().getPlayer();
        final Pit oponentBigPit = oponent.getPlayerBigPit();
        int currentStones = emptiedPit.getnStones();
        if (currentStones == 0) {
            return "You cannot select an empty pit!";
        }
        emptiedPit.emptyPit();
        Pit currentPit = emptiedPit.getNextPit();
        boolean changePlayer = true;
        Pit lastPit = null;
        while (currentStones-- > 0) {
            currentPit.addOneStone();
            lastPit = currentPit;
            currentPit = currentPit.getNextPitNotThisOne(oponentBigPit);
        }
        if (lastPit != currentPlayer.getPlayerBigPit()) {
            if (lastPit.getOpositePit().getPlayer() != currentPlayer) {
                lastPit.addStones(lastPit.getOpositePit().getnStones());
                lastPit.getOpositePit().emptyPit();
            }
            changePlayer();
        }
        gameOver = board.getPlayer1().arePitsEmpty() || board.getPlayer2().arePitsEmpty();
        return "";
    }

    @Override
    public void changePlayer() {
        if (currentPlayer == board.getPlayer1()) {
            currentPlayer = board.getPlayer2();
        } else {
            currentPlayer = board.getPlayer1();
        }
    }

    @Override
    public Board getBoard() {
        return board;
    }


    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void startBoard() {
        this.board = new BoardImpl(6, 6, new PlayerImpl(1, "Player One"), new PlayerImpl(2, "Player Two"));
        this.currentPlayer = board.getPlayer1();
        gameOver = false;
    }
}
