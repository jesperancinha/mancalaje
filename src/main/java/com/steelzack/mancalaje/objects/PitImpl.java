package com.steelzack.mancalaje.objects;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class PitImpl implements Pit {
    private Pit opositePit;

    private Player player;
    private String sharedKey;

    private Pit nextPit;

    private int nStones;

    public PitImpl(int nInitialStones, Player player, String sharedKey) {
        this.nStones = nInitialStones;
        this.player = player;
        this.sharedKey = sharedKey;
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
    public Pit getNextPitNotThisOne(Pit oponentBigPit) {
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

    @Override
    public String getSharedKey() {
        return sharedKey;
    }

    @Override
    public void addStones(Integer nStones) {
        this.nStones += nStones;
    }
}
