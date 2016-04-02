package com.jfse.stonesgame.objects;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class PitImpl implements Pit{
    private Pit opositePit;

    private Player player;

    private Pit nextPit;

    private int nInitialStones;

    public PitImpl(int nInitialStones, Player player) {
        this.nInitialStones = nInitialStones;
        this.player = player;
    }

    public void setNextPit(Pit nextPit) {
        this.nextPit = nextPit;
    }

    public void setOpositePit(Pit opositePit) {
        this.opositePit = opositePit;
    }
}
