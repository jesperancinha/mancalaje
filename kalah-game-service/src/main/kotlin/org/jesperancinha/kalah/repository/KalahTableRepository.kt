package org.jesperancinha.kalah.repository

import org.jesperancinha.kalah.model.KalahTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface KalahTableRepository : JpaRepository<KalahTable?, Long?>