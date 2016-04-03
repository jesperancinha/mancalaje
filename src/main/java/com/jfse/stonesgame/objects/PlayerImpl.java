package com.jfse.stonesgame.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class PlayerImpl implements Player {
    private Pit playerBigPit;

    private final List<Pit> ownedPits;

    public PlayerImpl() {
        ownedPits = new ArrayList<>();
    }

    @Override
    public void setPlayerBigPit(Pit playerBigPit) {
        this.playerBigPit = playerBigPit;
    }

    @Override
    public void addPit(Pit pit) {
        ownedPits.add(pit);
    }

    @Override
    public Pit getPlayerBigPit() {
        return playerBigPit;
    }

    @Override
    public List<Pit> getOwnedPits() {
        return ownedPits;
    }
}
