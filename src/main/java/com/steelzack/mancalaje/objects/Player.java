package com.steelzack.mancalaje.objects;

import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 02-04-16.
 */
public interface Player {

    void setPlayerBigPit(Pit playerBigPit);

    void addPit(Pit pit);

    Pit getPlayerBigPit();

    List<Pit> getOwnedPits();

    Integer getPlayerId();

    String getPlayerName();

    void orderPits();

    void invertPits();

    boolean arePitsEmpty();

    String getSessionId();

    void setSessionId(String sessionId);
}
