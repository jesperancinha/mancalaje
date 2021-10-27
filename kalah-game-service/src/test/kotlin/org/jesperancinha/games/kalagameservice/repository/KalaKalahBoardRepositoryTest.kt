package org.jesperancinha.games.kalagameservice.repository

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.jesperancinha.games.kalagameservice.model.KalahBoard
import org.jesperancinha.games.kalagameservice.model.KalahWasher
import org.jesperancinha.games.kalagameservice.model.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional
import java.util.UUID.randomUUID

@DataJpaTest
@Transactional
internal class KalaKalahBoardRepositoryTest(
    @Autowired
    private val kalaPitRepository: KalaPitRepository,

    @Autowired
    private val kalaBoardRepository: KalaBoardRepository,

    @Autowired
    private val kalaPlayerRepository: KalaPlayerRepository
) {
    val player1 = kalaPlayerRepository.save(
        Player(
            username = "player1${randomUUID()}"
        )
    )
    val player2 = kalaPlayerRepository.save(
        Player(
            username = "player2${java.util.UUID.randomUUID()}"
        )
    )
    val player3 = kalaPlayerRepository.save(
        Player(
            username = "player3${java.util.UUID.randomUUID()}"
        )
    )

    @BeforeEach
    fun setUp() {
        val kalahWasherOne = KalahWasher()
        kalaPitRepository.save(kalahWasherOne)
        val kalahBoardPlayerOne = KalahBoard(playerOne = player1, kalahWasherOne = kalahWasherOne)
        val kalahBoardPlayerTwo = KalahBoard(playerOne = player2, kalahWasherOne = kalahWasherOne)
        val kalahBoardPlayerThree = KalahBoard(playerOne = player3, playerTwo = player2, kalahWasherOne = kalahWasherOne)
        kalaBoardRepository.save(kalahBoardPlayerOne)
        kalaBoardRepository.save(kalahBoardPlayerTwo)
        kalaBoardRepository.save(kalahBoardPlayerThree)
    }

    @Test
    fun testFindBoardsByPlayerOneEquals_whenPlayerOne_thenShowPlayerOneBoards() {
        val boardsByPlayerOneEquals = kalaBoardRepository.findBoardsByPlayerOneEquals(player1)
        boardsByPlayerOneEquals.shouldHaveSize(1)
        boardsByPlayerOneEquals[0]?.playerOne?.username shouldBe player1.username
    }

    @Test
    fun testFindBoardsByPlayerOneEquals_whenPlayerThree_thenShowPlayerThreeBoards() {
        val boardsByPlayerOneEquals = kalaBoardRepository.findBoardsByPlayerOneEquals(player3)
        boardsByPlayerOneEquals.shouldHaveSize(1)
        boardsByPlayerOneEquals[0]?.playerOne?.username shouldBe player3.username
    }

    @Test
    fun findBoardsByPlayerTwoIsNull_whenCalled_thenShowAllBoards() {
        val boardsByPlayerTwoIsNull = kalaBoardRepository.findBoardsByPlayerTwoIsNull()
        boardsByPlayerTwoIsNull.shouldHaveSize(2)
        boardsByPlayerTwoIsNull[0]?.playerOne?.username shouldBe player1.username
        boardsByPlayerTwoIsNull[1]?.playerOne?.username shouldBe player2.username
    }
}