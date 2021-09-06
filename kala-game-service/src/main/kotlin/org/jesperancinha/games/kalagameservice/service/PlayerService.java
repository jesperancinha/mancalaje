package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Player;

public interface PlayerService {
    Player createPlayer(String username);

    Player createOrFindPlayerByName(String username);

    void leaveCurrentGame(String name);
}
