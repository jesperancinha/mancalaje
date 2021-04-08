package org.jesperancinha.games.kalagameservice.repository;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KalaBoardRepository extends JpaRepository<Board, Long> {
}
