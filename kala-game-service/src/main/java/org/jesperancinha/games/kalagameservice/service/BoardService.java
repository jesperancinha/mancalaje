package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Board;

public interface BoardService {
    Board findBoardById(final Long id);
}
