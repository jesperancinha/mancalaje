package org.jesperancinha.kalah.repository

import org.jesperancinha.kalah.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
interface KalahBoardRepository : JpaRepository<KalahBoard, UUID> {
    fun findBoardsByPlayerOneEquals(player: Player): List<KalahBoard>
    fun findBoardsByPlayerTwoIsNull(): List<KalahBoard>
}

@Transactional
interface KalahCupRepository : JpaRepository<KalahCup?, Long?>

interface KalahPlayerRepository : JpaRepository<Player, UUID?> {
    fun findPlayerByUsernameEquals(username: String): Player
}

@Transactional
interface KalahTableRepository : JpaRepository<KalahTable?, Long?>
interface KalahWasherRepository : JpaRepository<KalahWasher, UUID>