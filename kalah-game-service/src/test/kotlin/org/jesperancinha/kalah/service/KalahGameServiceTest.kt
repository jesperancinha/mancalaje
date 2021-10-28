package org.jesperancinha.kalah.service

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.jesperancinha.kalah.model.KalahWasher
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
        gameBoard.kalahWasherOne.shouldNotBeNull()
        gameBoard.kalahWasherTwo.shouldNotBeNull()
        gameBoard.kalahTableOne.shouldNotBeNull()
        gameBoard.kalahTableTwo.shouldNotBeNull()
        gameBoard.owner shouldBeSameInstanceAs player
        gameBoard.winner.shouldBeNull()
        gameBoard.version.shouldBeNull()

        gameBoard.kalahWasherOne shouldHaveWashersConnectionSizeOf 6
        gameBoard.kalahWasherTwo shouldHaveWashersConnectionSizeOf 6

        gameBoard.shouldNotBeNull()
    }

    @Test
    fun sowStonesFromPit() {
    }

    @Test
    fun joinPlayer() {
    }
}

infix fun KalahWasher?.shouldHaveWashersConnectionSizeOf(size: Int) {
    if (size == 1) {
        this?.nextKalahWasher.shouldBeNull()
        this?.nextKalahTable.shouldNotBeNull()
    } else {
        this?.nextKalahWasher.shouldNotBeNull()
        this?.nextKalahTable.shouldBeNull()
        this?.nextKalahWasher shouldHaveWashersConnectionSizeOf (size - 1)
    }
}