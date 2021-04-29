package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Pit;
import org.jesperancinha.games.kalagameservice.model.Player;

public interface GameService {
    Board createNewBoard(final Player player);

    Board sowStonesFromPit(final Player player, final Pit pit, final Board board);

    Board joinPlayer(final Player player, final Board board);
}
