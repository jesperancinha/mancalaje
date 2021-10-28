package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalahPlayerRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

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