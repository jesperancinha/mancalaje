package org.jesperancinha.kalah.service

import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.repository.KalahWasherRepository
import org.springframework.stereotype.Service

@Service
class KalahWasherService(
    val kalahWasherRepository: KalahWasherRepository
) {
    /**
     * This method will trigger any PointCut of an Aspect
     */
    fun create(kalahWasher: KalahWasher): KalahWasher = kalahWasherRepository.save(kalahWasher)

    /**
     * This method does not trigger any PointCut
     */
    fun update(kalahWasher: KalahWasher): KalahWasher = kalahWasherRepository.save(kalahWasher)
}