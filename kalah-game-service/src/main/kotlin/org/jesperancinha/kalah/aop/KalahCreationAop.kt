package org.jesperancinha.kalah.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.jesperancinha.kalah.model.KalahCup
import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.repository.KalahCupRepository
import org.springframework.stereotype.Component

@Aspect
@Component
class KalahCreationAop(
    val kalahCupRepository: KalahCupRepository
) {

    @Before("execution(* org.jesperancinha.kalah.service.KalahWasherService.create(..)) and args(kalahWasher)")
    fun createCups(joinPoint: JoinPoint, kalahWasher: KalahWasher) {
        val kalahCup1 = kalahCupRepository.save(KalahCup(full = false, washer = kalahWasher))
        val kalahCup2 = kalahCupRepository.save(KalahCup(full = false, washer = kalahWasher))
        val kalahCup3 = kalahCupRepository.save(KalahCup(full = false, washer = kalahWasher))
        kalahWasher.cups.addAll(mutableListOf(kalahCup1, kalahCup2, kalahCup3))
    }
}