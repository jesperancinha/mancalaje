package org.jesperancinha.kalah.service

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
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
    fun `should rollout cups on the first move and the first washer`() {
        val player = playerRepository.save(Player(username = "joao"))
        val gameBoard = gameService.createNewBoard(player = player)

//        gameService.sowCupsFromWasher()
    }

    @Test
    fun `should join player one and player two, register them as opponents and associate the correct table and washer`() {
        val playerOne = playerRepository.save(Player(username = "joao"))
        val playerTwo = playerRepository.save(Player(username = "submarine"))
        val gameBoard = gameService.createNewBoard(player = playerOne)
        val gameBoardUpdate1 = gameService.joinPlayer(playerOne, kalahBoard = gameBoard)

        gameBoardUpdate1.playerOne shouldBe playerOne
        gameBoardUpdate1.playerTwo.shouldBeNull()
        gameBoardUpdate1.kalahTableOne.shouldNotBeNull()
        gameBoardUpdate1.kalahTableOne?.player shouldBe playerOne
        gameBoardUpdate1.kalahTableTwo.shouldNotBeNull()
        gameBoardUpdate1.kalahTableTwo?.player.shouldBeNull()

        val gameBoardUpdate2 = gameService.joinPlayer(playerTwo, kalahBoard = gameBoard)

        gameBoardUpdate2.playerTwo shouldBe playerTwo
        gameBoardUpdate2.playerOne shouldBe playerOne
        gameBoardUpdate2.playerOne.shouldNotBeNull()
        gameBoardUpdate2.playerOne?.opponent shouldBe playerTwo
        gameBoardUpdate2.playerTwo.shouldNotBeNull()
        gameBoardUpdate2.playerTwo?.opponent shouldBe playerOne
        gameBoardUpdate1.kalahTableOne.shouldNotBeNull()
        gameBoardUpdate1.kalahTableOne?.player shouldBe playerOne
        gameBoardUpdate1.kalahTableTwo.shouldNotBeNull()
        gameBoardUpdate1.kalahTableTwo?.player shouldBe playerTwo
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