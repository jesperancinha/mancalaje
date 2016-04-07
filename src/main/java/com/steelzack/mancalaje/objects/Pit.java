package com.steelzack.mancalaje.objects;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public interface Pit {

    void setNextPit(Pit nextPit);

    void setOpositePit(Pit opositePit);

    Integer getnStones();

    Pit getNextPit();

    Player getPlayer();

    Pit getOpositePit();

    void emptyPit();

    Pit getNextPitNotThisOne(Pit oponentBigPit);

    void addOneStone();

    String getSharedKey();

    void addStones(Integer integer);
}
