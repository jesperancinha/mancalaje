package com.jfse.stonesgame.objects;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class BigPitImpl extends PitImpl {

    public BigPitImpl(int nStones, Player player) {
        super(nStones, player);
    }

    @Override
    public void setNextPit(Pit nextPit) {
        super.setNextPit(nextPit);
    }
}
