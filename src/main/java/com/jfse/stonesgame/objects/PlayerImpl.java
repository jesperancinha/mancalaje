package com.jfse.stonesgame.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class PlayerImpl implements Player {
    private Pit playerBigPit;

    private final List<Pit> ownedPits;

    private Integer playerId;

    private String playerName;

    public PlayerImpl(Integer playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
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

    @Override
    public Integer getPlayerId() {
        return playerId;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }
}
