package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.*;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class BoardManagerImpl implements BoardManager {

    private Board board;

    private Player currentPlayer;

    private boolean gameOver;
    private Player winner;

    public BoardManagerImpl( //
            String playerOneName, //
            String sessionId1, //
            String playerTwoName, //
            String sessionId2) { //
        startBoard( //
                playerOneName, //
                sessionId1, //
                playerTwoName, //
                sessionId2 //
        );
    }

    BoardManagerImpl(int nPits, int nInitialStones, Player player1, Player player2) {
        this.board = new BoardImpl(nPits, nInitialStones, player1, player2);
        currentPlayer = player1;
    }

    @Override
    public String moveStones(String chosenPitKey, String sessionId) {
        final Pit emptiedPit = this.board.getPitMap().get(chosenPitKey);
        if (emptiedPit.getPlayer() != currentPlayer) {
            return "Invalid move!";
        }

        if (!emptiedPit.getPlayer().getSessionId().equals(sessionId)) {
            return "It's not your turn!";
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
        final Player player1 = board.getPlayer1();
        final Player player2 = board.getPlayer2();
        gameOver = player1.arePitsEmpty() || player2.arePitsEmpty();
        if (gameOver) {
            collectStones(player1);
            collectStones(player2);
            decideWinner(player1, player2);
        }

        return "";
    }

    @Override
    public void decideWinner(Player player1, Player player2) {
        final Integer playerOneStones = player1.getPlayerBigPit().getnStones();
        final Integer playerTwoStones = player2.getPlayerBigPit().getnStones();
        if (playerOneStones > playerTwoStones) {
            winner = player1;
        } else if (playerOneStones < playerTwoStones) {
            winner = player2;
        } else {
            winner = null;
        }
    }

    @Override
    public void collectStones(Player player) {
        int totalStones = 0;
        for (Pit p : player.getOwnedPits()) {
            totalStones += p.getnStones();
            p.emptyPit();
        }
        player.getPlayerBigPit().addStones(totalStones);
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
    public void startBoard( //
                            String playerOneName, //
                            String sessionId1, //
                            String playerTwoName, //
                            String sessionId2) { //
        this.board = new BoardImpl( //
                6, //
                6, //
                new PlayerImpl(1, playerOneName, sessionId1), //
                new PlayerImpl(2, playerTwoName, sessionId2) //
        );
        this.currentPlayer = board.getPlayer1();
        gameOver = false;
    }

    @Override
    public Player getWinner() {
        return winner;
    }
}
