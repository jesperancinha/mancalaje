package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.model.KalahWasher
import org.jesperancinha.games.kalagameservice.repository.KalahWasherRepository
import org.springframework.stereotype.Service

@Service
class KalahWasherService(
    val kalahWasherRepository: KalahWasherRepository
) {
    fun create(kalahWasher: KalahWasher): KalahWasher = kalahWasherRepository.save(kalahWasher)
}