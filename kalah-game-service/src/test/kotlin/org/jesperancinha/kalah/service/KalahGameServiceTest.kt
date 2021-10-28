package org.jesperancinha.kalah.service

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.jesperancinha.kalah.model.Player
import org.jesperancinha.kalah.repository.KalahPlayerRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Transactional
internal class KalahGameServiceTest(
    @Autowired
    val gameService: KalahGameService,
    @Autowired
    val playerRepository: KalahPlayerRepository
) {

    @Test
    fun `should create a simple board everytime it gets called`() {
        val player = playerRepository.save(Player(username = "joao"))
        val gameBoard = gameService.createNewBoard(player = player)

        gameBoard.kalahWashers.shouldNotBeNull()
        gameBoard.kalahWashers.shouldNotBeEmpty()
        gameBoard.kalahWashers?.shouldHaveSize(12)

        gameBoard.currentPlayer.shouldBeNull()
        gameBoard.playerOne.shouldBeNull()
        gameBoard.playerTwo.shouldBeNull()
        gameBoard.kalahOne.shouldNotBeNull()
        gameBoard.kalahTwo.shouldNotBeNull()
        gameBoard.owner shouldBeSameInstanceAs player
        gameBoard.winner.shouldBeNull()
        gameBoard.version.shouldBeNull()

        gameBoard.shouldNotBeNull()
    }

    @Test
    fun sowStonesFromPit() {
    }

    @Test
    fun joinPlayer() {
    }
}