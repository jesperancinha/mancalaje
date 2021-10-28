package org.jesperancinha.kalah.repository

import org.jesperancinha.kalah.model.KalahCup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface KalahCupRepository : JpaRepository<KalahCup?, Long?> {
}