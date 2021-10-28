package org.jesperancinha.kalah.repository

import org.jesperancinha.kalah.model.KalahBoard
import org.jesperancinha.kalah.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface KalahBoardRepository : JpaRepository<KalahBoard?, Long?> {
    fun findBoardsByPlayerOneEquals(player: Player?): List<KalahBoard?>
    fun findBoardsByPlayerTwoIsNull(): List<KalahBoard?>
}