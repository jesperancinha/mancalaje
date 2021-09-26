package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
open class PlayerServiceImpl(private val playerRepository: KalaPlayerRepository) : PlayerService {
    override fun createPlayer(username: String?): Player {
        return playerRepository.save(Player(
            username = username))
    }

    @Transactional
    override fun createOrFindPlayerByName(username: String): Player? {
        val playerByUsernameEquals = playerRepository.findPlayerByUsernameEquals(username)
        return if (Objects.isNull(playerByUsernameEquals)) {
            createPlayer(username)
        } else playerByUsernameEquals
    }

    override fun leaveCurrentGame(name: String?) {
        val player = playerRepository.findPlayerByUsernameEquals(name)
        val currentBoard = player?.currentBoard
        if (Objects.nonNull(currentBoard)) {
            val opponent = player?.opponent
            if (Objects.nonNull(opponent)) {
                opponent?.currentBoard = null
                playerRepository.save(opponent)
            }
            player?.currentBoard = null
            playerRepository.save(player)
        }
    }
}