package com.steelzack.mancalaje.objects;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class BigPitImpl extends PitImpl {

    public BigPitImpl(int nStones, Player player, String sharedKey) {
        super(nStones, player, sharedKey);
    }

    @Override
    public void setNextPit(Pit nextPit) {
        super.setNextPit(nextPit);
    }
}
