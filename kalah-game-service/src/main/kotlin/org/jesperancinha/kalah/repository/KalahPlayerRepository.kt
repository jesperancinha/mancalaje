package org.jesperancinha.kalah.repository

import org.jesperancinha.kalah.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface KalahPlayerRepository : JpaRepository<Player?, UUID?> {
    fun findPlayerByUsernameEquals(username: String): Player
}