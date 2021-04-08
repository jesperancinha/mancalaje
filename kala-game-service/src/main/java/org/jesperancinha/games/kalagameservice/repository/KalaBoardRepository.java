package org.jesperancinha.games.kalagameservice.repository;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KalaBoardRepository extends JpaRepository<Board, Long> {

    List<Board> findBoardsByPlayerOneEquals(Player player);
}
