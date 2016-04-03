package com.jfse.stonesgame.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.jfse.stonesgame.objects.BoardImpl;
import com.jfse.stonesgame.objects.Pit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
@JsonAutoDetect
public class BoardModel {

    private PitModel largePit1;
    private PitModel largePit2;

    private String currentPlayerName;
    private Integer currentPlayerId;

    private List<PitModel> pits1;
    private List<PitModel> pits2;

    private String errorMessage;

    private boolean gameOver;
    private String winnerPlayerName;

    public BoardModel(Pit largePit1, //
                      Pit largePit2, //
                      List<Pit> pits1, //
                      List<Pit> pits2, //
                      String currentPlayerName, //
                      Integer currentPlayerId, //
                      String errorMessage, //
                      boolean gameOver, //
                      String winnerPlayerName) {

        this.currentPlayerName = currentPlayerName;
        this.currentPlayerId = currentPlayerId;
        this.winnerPlayerName = winnerPlayerName;

        this.largePit1 = new PitModel(largePit1.getnStones(), BoardImpl.PLAYER1);
        this.largePit2 = new PitModel(largePit2.getnStones(), BoardImpl.PLAYER2);

        this.pits1 = convertPitsToModel(pits1);
        this.pits2 = convertPitsToModel(pits2);

        this.errorMessage = errorMessage;
        this.gameOver = gameOver;
    }

    private List<PitModel> convertPitsToModel(List<Pit> pits) {
        List<PitModel> modelPits = new ArrayList<>();
        for (Pit p : pits) {
            modelPits.add(new PitModel(p.getnStones(), p.getSharedKey()));
        }
        return modelPits;
    }

    public PitModel getLargePit1() {
        return largePit1;
    }

    public PitModel getLargePit2() {
        return largePit2;
    }

    public List<PitModel> getPits1() {
        return pits1;
    }

    public List<PitModel> getPits2() {
        return pits2;
    }

    public Integer getCurrentPlayerId() {
        return currentPlayerId;
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinnerPlayerName() {
        return winnerPlayerName;
    }
}
