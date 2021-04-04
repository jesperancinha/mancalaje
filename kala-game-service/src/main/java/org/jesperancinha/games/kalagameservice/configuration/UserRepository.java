package org.jesperancinha.games.kalagameservice.configuration;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface UserRepository extends ReactiveSortingRepository<User, String> {
}