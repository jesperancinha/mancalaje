package org.jesperancinha.kalah.repository

import org.jesperancinha.kalah.model.KalahWasher
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface KalahWasherRepository : JpaRepository<KalahWasher, UUID>