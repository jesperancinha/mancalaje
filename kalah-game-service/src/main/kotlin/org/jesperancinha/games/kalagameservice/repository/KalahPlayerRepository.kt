package org.jesperancinha.games.kalagameservice.repository

import org.jesperancinha.games.kalagameservice.model.Player
import org.springframework.data.jpa.repository.JpaRepository

interface KalahPlayerRepository : JpaRepository<Player?, Long?> {
    fun findPlayerByUsernameEquals(username: String): Player
}