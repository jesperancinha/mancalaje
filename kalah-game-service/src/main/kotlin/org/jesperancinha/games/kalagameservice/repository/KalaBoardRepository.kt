package org.jesperancinha.games.kalagameservice.repository

import org.jesperancinha.games.kalagameservice.model.KalahBoard
import org.jesperancinha.games.kalagameservice.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface KalaBoardRepository : JpaRepository<KalahBoard?, Long?> {
    fun findBoardsByPlayerOneEquals(player: Player?): List<KalahBoard?>
    fun findBoardsByPlayerTwoIsNull(): List<KalahBoard?>
}