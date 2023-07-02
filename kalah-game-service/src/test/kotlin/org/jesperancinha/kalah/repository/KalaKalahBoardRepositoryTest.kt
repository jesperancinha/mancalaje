package org.jesperancinha.kalah.repository

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.jesperancinha.kalah.containers.AbstractTestContainersIT
import org.jesperancinha.kalah.containers.AbstractTestContainersIT.DockerPostgresDataInitializer
import org.jesperancinha.kalah.model.KalahBoard
import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.model.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import java.util.UUID.randomUUID

@SpringBootTest
@Transactional
@ContextConfiguration(initializers = [DockerPostgresDataInitializer::class])
internal class KalaKalahBoardRepositoryTest(
    @Autowired
    private val kalahWasherRepository: KalahWasherRepository,

    @Autowired
    private val kalahBoardRepository: KalahBoardRepository,

    @Autowired
    private val kalahPlayerRepository: KalahPlayerRepository
) {
    val player1 = kalahPlayerRepository.save(
        Player(
            username = "player1${randomUUID()}"
        )
    )
    val player2 = kalahPlayerRepository.save(
        Player(
            username = "player2${randomUUID()}"
        )
    )
    val player3 = kalahPlayerRepository.save(
        Player(
            username = "player3${randomUUID()}"
        )
    )

    @BeforeEach
    fun setUp() {
        val kalahWasherOne = kalahWasherRepository.save(KalahWasher())
        val kalahBoardPlayerOne = KalahBoard(playerOne = player1, kalahWasherOne = kalahWasherOne, owner = player1)
        val kalahBoardPlayerTwo = KalahBoard(playerOne = player2, kalahWasherOne = kalahWasherOne, owner = player1)
        val kalahBoardPlayerThree =
            KalahBoard(playerOne = player3, playerTwo = player2, kalahWasherOne = kalahWasherOne, owner = player1)
        kalahBoardRepository.save(kalahBoardPlayerOne)
        kalahBoardRepository.save(kalahBoardPlayerTwo)
        kalahBoardRepository.save(kalahBoardPlayerThree)
    }

    @Test
    fun testFindBoardsByPlayerOneEquals_whenPlayerOne_thenShowPlayerOneBoards() {
        val boardsByPlayerOneEquals = kalahBoardRepository.findBoardsByPlayerOneEquals(player1)
        boardsByPlayerOneEquals.shouldHaveSize(1)
        boardsByPlayerOneEquals[0].playerOne?.username shouldBe player1.username
    }

    @Test
    fun testFindBoardsByPlayerOneEquals_whenPlayerThree_thenShowPlayerThreeBoards() {
        val boardsByPlayerOneEquals = kalahBoardRepository.findBoardsByPlayerOneEquals(player3)
        boardsByPlayerOneEquals.shouldHaveSize(1)
        boardsByPlayerOneEquals[0].playerOne?.username shouldBe player3.username
    }

    @Test
    fun findBoardsByPlayerTwoIsNull_whenCalled_thenShowAllBoards() {
        val boardsByPlayerTwoIsNull = kalahBoardRepository.findBoardsByPlayerTwoIsNull()
        boardsByPlayerTwoIsNull.shouldHaveSize(2)
        boardsByPlayerTwoIsNull[0]?.playerOne?.username shouldBe player1.username
        boardsByPlayerTwoIsNull[1]?.playerOne?.username shouldBe player2.username
    }
}