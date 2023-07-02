package org.jesperancinha.kalah.service

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.jesperancinha.kalah.containers.AbstractTestContainersIT
import org.jesperancinha.kalah.containers.AbstractTestContainersIT.DockerPostgresDataInitializer
import org.jesperancinha.kalah.model.KalahCup
import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.model.Player
import org.jesperancinha.kalah.repository.KalahPlayerRepository
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = [DockerPostgresDataInitializer::class])
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
    @Disabled
    fun `should rollout cups on the first move and the first washer`() {
        val playerOne = playerRepository.save(Player(username = "joao"))
        val playerTwo = playerRepository.save(Player(username = "submarine"))
        val gameBoard = gameService.createNewBoard(player = playerOne)
        gameService.joinPlayer(playerOne, kalahBoard = gameBoard)
        val testGameBoard = gameService.joinPlayer(playerTwo, kalahBoard = gameBoard)
        testGameBoard.currentPlayer = playerOne
        val rolloutCupsFromPayersWasherOnBoard =
            gameService.rolloutCupsFromPayersWasherOnBoard(playerOne, gameBoard.kalahWasherOne, testGameBoard)

        val firstWasher = rolloutCupsFromPayersWasherOnBoard.kalahWasherOne
        firstWasher?.cups.shouldBeEmpty()
        val secondWasher = firstWasher?.nextKalahWasher
        secondWasher?.cups?.size shouldBe 4
        val thirdWasher = secondWasher?.nextKalahWasher
        thirdWasher?.cups?.size shouldBe 4
        val fourthWasher = thirdWasher?.nextKalahWasher
        fourthWasher?.cups?.size shouldBe 4
        val fifthWasher = fourthWasher?.nextKalahWasher
        fifthWasher?.cups?.size shouldBe 3
        val sixthWasher = fifthWasher?.nextKalahWasher
        sixthWasher?.cups?.size shouldBe 3
        sixthWasher?.nextKalahWasher.shouldBeNull()
        sixthWasher?.nextKalahTable.shouldNotBeNull()
        sixthWasher?.nextKalahTable?.cups.shouldBeEmpty()
    }


    @Test
    @Disabled
    fun `should rollout cups on the first move and the last washer`() {
        val playerOne = playerRepository.save(Player(username = "joao"))
        val playerTwo = playerRepository.save(Player(username = "submarine"))
        val gameBoard = gameService.createNewBoard(player = playerOne)
        gameService.joinPlayer(playerOne, kalahBoard = gameBoard)
        val testGameBoard = gameService.joinPlayer(playerTwo, kalahBoard = gameBoard)
        testGameBoard.currentPlayer = playerOne
        val firstWasher = testGameBoard.kalahWasherOne
        val secondWasher = firstWasher?.nextKalahWasher
        val thirdWasher = secondWasher?.nextKalahWasher
        val fourthWasher = thirdWasher?.nextKalahWasher
        val fifthWasher = fourthWasher?.nextKalahWasher
        val sixthWasher = fifthWasher?.nextKalahWasher

        gameService.rolloutCupsFromPayersWasherOnBoard(playerOne, sixthWasher, testGameBoard)

        firstWasher?.cups?.size shouldBe 3
        secondWasher?.cups?.size shouldBe 3
        thirdWasher?.cups?.size shouldBe 3
        fourthWasher?.cups?.size shouldBe 3
        fifthWasher?.cups?.size shouldBe 3
        sixthWasher?.cups?.shouldBeEmpty()
        sixthWasher?.nextKalahWasher.shouldBeNull()
        sixthWasher?.nextKalahTable.shouldNotBeNull()
        sixthWasher?.nextKalahTable?.cups?.size shouldBe 1
        val p2FirstWasher = sixthWasher?.nextKalahTable?.nextKalahWasher
        p2FirstWasher?.cups?.size shouldBe 4
        val p2SecondWasher = p2FirstWasher?.nextKalahWasher
        p2SecondWasher?.cups?.size shouldBe 4
        val p2ThirdWasher = p2SecondWasher?.nextKalahWasher
        p2ThirdWasher?.cups?.size shouldBe 3
        val p2FourthWasher = p2ThirdWasher?.nextKalahWasher
        p2FourthWasher?.cups?.size shouldBe 3
        val p2FifthWasher = p2FourthWasher?.nextKalahWasher
        p2FifthWasher?.cups?.size shouldBe 3
        val p2SixthWasher = p2FifthWasher?.nextKalahWasher
        p2SixthWasher?.cups?.size shouldBe 3
        p2SixthWasher?.nextKalahWasher.shouldBeNull()
        p2SixthWasher?.nextKalahTable?.cups?.size shouldBe 0
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
        gameBoardUpdate2.kalahTableOne.shouldNotBeNull()
        gameBoardUpdate2.kalahTableOne?.player shouldBe playerOne
        gameBoardUpdate2.kalahTableTwo.shouldNotBeNull()
        gameBoardUpdate2.kalahTableTwo?.player shouldBe playerTwo

        gameBoardUpdate2.kalahWasherOne.shouldHaveWashersConnectionSizeOfWithPlayerAndNextTableOfPlayer(
            6,
            playerOne,
            playerTwo
        )
        gameBoardUpdate2.kalahWasherTwo.shouldHaveWashersConnectionSizeOfWithPlayerAndNextTableOfPlayer(
            6,
            playerTwo,
            playerOne
        )
    }

    @Test
    @Disabled
    fun `should randomly remove 3 cups from the opponents display which has 8 leaving a total of 5`() {
        val playerOne = playerRepository.save(Player(username = "joao"))
        val playerTwo = playerRepository.save(Player(username = "submarine"))
        val gameBoard = gameService.createNewBoard(player = playerOne)
        gameService.joinPlayer(playerOne, kalahBoard = gameBoard)
        val testGameBoard = gameService.joinPlayer(playerTwo, kalahBoard = gameBoard)
        testGameBoard.currentPlayer = playerOne
        val firstWasher = testGameBoard.kalahWasherOne
        val secondWasher = firstWasher?.nextKalahWasher
        val thirdWasher = secondWasher?.nextKalahWasher
        val fourthWasher = thirdWasher?.nextKalahWasher
        val fifthWasher = fourthWasher?.nextKalahWasher
        val sixthWasher = fifthWasher?.nextKalahWasher
        repeat(8) {
            sixthWasher?.nextKalahTable?.cups?.add(KalahCup(full = false))
        }

        gameService.rolloutCupsFromPayersWasherOnBoard(playerOne, fourthWasher, testGameBoard)

        firstWasher?.cups?.size shouldBe 3
        secondWasher?.cups?.size shouldBe 3
        thirdWasher?.cups?.size shouldBe 3
        fourthWasher?.cups?.shouldBeEmpty()
        fifthWasher?.cups?.size shouldBe 4
        sixthWasher?.cups?.size shouldBe 4
        sixthWasher?.nextKalahWasher.shouldBeNull()
        sixthWasher?.nextKalahTable.shouldNotBeNull()
        sixthWasher?.nextKalahTable?.cups?.size shouldBe 6
        val p2FirstWasher = sixthWasher?.nextKalahTable?.nextKalahWasher
        p2FirstWasher?.cups?.size shouldBe 3
        val p2SecondWasher = p2FirstWasher?.nextKalahWasher
        p2SecondWasher?.cups?.size shouldBe 3
        val p2ThirdWasher = p2SecondWasher?.nextKalahWasher
        p2ThirdWasher?.cups?.size shouldBe 3
        val p2FourthWasher = p2ThirdWasher?.nextKalahWasher
        p2FourthWasher?.cups?.size shouldBe 3
        val p2FifthWasher = p2FourthWasher?.nextKalahWasher
        p2FifthWasher?.cups?.size shouldBe 3
        val p2SixthWasher = p2FifthWasher?.nextKalahWasher
        p2SixthWasher?.cups?.size shouldBe 3
        p2SixthWasher?.nextKalahWasher.shouldBeNull()
        p2SixthWasher?.nextKalahTable?.cups?.size shouldBe 0
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

fun KalahWasher?.shouldHaveWashersConnectionSizeOfWithPlayerAndNextTableOfPlayer(
    size: Int,
    player: Player,
    player2: Player
) {
    if (size == 1) {
        this?.nextKalahWasher.shouldBeNull()
        this?.nextKalahTable.shouldNotBeNull().also { it.player shouldBe player2 }
    } else {
        this?.nextKalahWasher.shouldNotBeNull().also { it.player shouldBe player }
        this?.nextKalahTable.shouldBeNull()
        this?.nextKalahWasher shouldHaveWashersConnectionSizeOf (size - 1)
    }
}