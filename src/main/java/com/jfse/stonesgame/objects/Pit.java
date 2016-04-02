package com.jfse.stonesgame.objects;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public interface Pit {

    void setNextPit(Pit nextPit);

    void setOpositePit(Pit opositePit);

    int getnStones();

    Pit getNextPit();

    Player getPlayer();

    Pit getOpositePit();
}
