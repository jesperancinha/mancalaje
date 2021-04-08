package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Stone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KalaStoneRepository extends JpaRepository<Stone, Long> {
}
