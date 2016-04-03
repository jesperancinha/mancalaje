package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.Board;
import com.jfse.stonesgame.objects.Player;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
public interface BoardManager {
    void moveStones(String chosenPitKey);

    void changePlayer();

    Board getBoard();

    void startBoard();

    Player getCurrentPlayer();
}
