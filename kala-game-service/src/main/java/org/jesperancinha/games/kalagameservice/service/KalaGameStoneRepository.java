package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface KalaGameStoneRepository extends ReactiveSortingRepository<Board, Long> {
}
