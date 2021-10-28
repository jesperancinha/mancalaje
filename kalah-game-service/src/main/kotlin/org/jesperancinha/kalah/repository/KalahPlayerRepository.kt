package org.jesperancinha.kalah.repository

import org.jesperancinha.kalah.model.Player
import org.springframework.data.jpa.repository.JpaRepository

interface KalahPlayerRepository : JpaRepository<Player?, Long?> {
    fun findPlayerByUsernameEquals(username: String): Player
}