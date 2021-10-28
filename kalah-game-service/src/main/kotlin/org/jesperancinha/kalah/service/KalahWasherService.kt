package org.jesperancinha.kalah.service

import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.repository.KalahWasherRepository
import org.springframework.stereotype.Service

@Service
class KalahWasherService(
    val kalahWasherRepository: KalahWasherRepository
) {
    fun create(kalahWasher: KalahWasher): KalahWasher = kalahWasherRepository.save(kalahWasher)
}