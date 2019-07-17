package com.jofisaes.mancala.cache;

public class Store extends Hole {

    public Store(Player player, int id) {
        super(player, id);
        this.setStones(0);
        this.setEnabled(false);
    }

    public void addStones(int flushPickedUpStones) {
        super.addStones(flushPickedUpStones);
    }
}
