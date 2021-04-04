package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Player;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface KalaGamePlayerRepository extends ReactiveSortingRepository<Player, Long> {
}
