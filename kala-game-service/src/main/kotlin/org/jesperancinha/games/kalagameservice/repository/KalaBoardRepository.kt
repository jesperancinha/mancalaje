package org.jesperancinha.games.kalagameservice.repository

import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Player
import org.springframework.data.jpa.repository.JpaRepository

interface KalaBoardRepository : JpaRepository<Board?, Long?> {
    fun findBoardsByPlayerOneEquals(player: Player?): List<Board?>
    fun findBoardsByPlayerTwoIsNull(): List<Board?>
}