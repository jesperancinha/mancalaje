package org.jesperancinha.games.kalagameservice.service

import io.kotest.matchers.nulls.shouldNotBeNull
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalahPlayerRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class GameServiceTest(
    @Autowired
    val gameService: GameService,
    @Autowired
    val playerRepository: KalahPlayerRepository
) {

    @Test
    fun createNewBoard() {
        val player = playerRepository.save(Player())
        val createNewBoard = gameService.createNewBoard(player = player)

        createNewBoard.shouldNotBeNull()
    }

    @Test
    fun sowStonesFromPit() {
    }

    @Test
    fun joinPlayer() {
    }
}