package com.jfse.stonesgame.manager;

import com.jfse.stonesgame.objects.Board;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
public interface BoardManager {
    void moveStones(String chosenPitKey);

    Board getBoard();

    void startBoard();
}
