package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.Board;
import com.jfse.stonesgame.objects.Player;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
public interface BoardManager {
    String moveStones(String chosenPitKey, String sessionId);

    void decideWinner(Player player1, Player player2);

    void collectStones(Player player1);

    void changePlayer();

    Board getBoard();

    void startBoard(String playerOneName, String sessionId1, String playerTwoName, String sessionId2);

    Player getCurrentPlayer();

    boolean isGameOver();

    Player getWinner();
}
