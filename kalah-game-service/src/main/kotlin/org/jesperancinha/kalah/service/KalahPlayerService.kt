package org.jesperancinha.kalah.service

import org.jesperancinha.kalah.model.Player
import org.jesperancinha.kalah.repository.KalahPlayerRepository
import org.springframework.stereotype.Service
import java.util.*
import jakarta.transaction.Transactional

@Service
class KalahPlayerService(private val playerRepository: KalahPlayerRepository) {
    fun createPlayer(username: String): Player {
        return playerRepository.save(
            Player(
                username = username,
            )
        )
    }

    @Transactional
    fun createOrFindPlayerByName(username: String): Player? {
        val playerByUsernameEquals = playerRepository.findPlayerByUsernameEquals(username)
        return if (Objects.isNull(playerByUsernameEquals)) {
            createPlayer(username)
        } else playerByUsernameEquals
    }

    fun leaveCurrentGame(name: String) {
        val player = playerRepository.findPlayerByUsernameEquals(name)
        val currentBoard = player.currentKalahBoard
        if (Objects.nonNull(currentBoard)) {
            val opponent = player.opponent
            if (Objects.nonNull(opponent)) {
                opponent?.currentKalahBoard = null
                opponent?.let { playerRepository.save(opponent) }

            }
            player.currentKalahBoard = null
            playerRepository.save(player)
        }
    }
}