package com.jfse.stonesgame.objects;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class PitImpl implements Pit {
    private Pit opositePit;

    private Player player;

    private Pit nextPit;

    private int nStones;

    public PitImpl(int nInitialStones, Player player) {
        this.nStones = nInitialStones;
        this.player = player;
    }

    @Override
    public void setNextPit(Pit nextPit) {
        this.nextPit = nextPit;
    }

    @Override
    public void setOpositePit(Pit opositePit) {
        this.opositePit = opositePit;
    }

    @Override
    public Integer getnStones() {
        return nStones;
    }

    @Override
    public Pit getNextPit() {
        return nextPit;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Pit getOpositePit() {
        return opositePit;
    }

    @Override
    public void emptyPit() {
        this.nStones = 0;
    }

    @Override
    public Pit getMextPitNotThisOne(Pit oponentBigPit) {
        if (nextPit == oponentBigPit) {
            return nextPit.getNextPit();
        } else {
            return nextPit;
        }
    }

    @Override
    public void addOneStone() {
        nStones++;
    }
}
