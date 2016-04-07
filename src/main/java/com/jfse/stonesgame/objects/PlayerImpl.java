package com.jfse.stonesgame.objects;

import java.util.*;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public class PlayerImpl implements Player {
    private Pit playerBigPit;

    private final List<Pit> ownedPits;

    private Integer playerId;

    private String playerName;

    private String sessionId;

    public PlayerImpl(Integer playerId, String playerName, String sessionId) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.ownedPits = new ArrayList<>();
        this.sessionId = sessionId;
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

    @Override
    public void orderPits() {
        Collections.sort(ownedPits, (o1, o2) -> o1.getSharedKey().compareTo(o2.getSharedKey()));
    }

    @Override
    public void invertPits() {
        Collections.sort(ownedPits, (o1, o2) -> o2.getSharedKey().compareTo(o1.getSharedKey()));

    }

    @Override
    public boolean arePitsEmpty() {
        boolean empty = true;
        for (Pit p : ownedPits) {
            if (p.getnStones() > 0) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
