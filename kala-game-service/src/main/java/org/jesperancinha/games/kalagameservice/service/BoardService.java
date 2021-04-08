package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.dto.BoardDto;
import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Player;

import java.util.List;

public interface BoardService {
    Board findBoardById(final Long id);

    List<BoardDto> findBoardsByPlayer(final Player player);

    List<BoardDto> findAvailableBoards();
}
