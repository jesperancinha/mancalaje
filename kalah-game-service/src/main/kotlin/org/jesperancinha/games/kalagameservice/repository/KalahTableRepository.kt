package org.jesperancinha.games.kalagameservice.repository

import org.jesperancinha.games.kalagameservice.model.KalahTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface KalahTableRepository : JpaRepository<KalahTable?, Long?>