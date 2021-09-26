package org.jesperancinha.games.kalagameservice.repository

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.transaction.Transactional

@DataJpaTest
internal open class KalaBoardRepositoryTest {
    @Autowired
    private val kalaBoardRepository: KalaBoardRepository? = null

    @Autowired
    private val kalaPlayerRepository: KalaPlayerRepository? = null
    private var player1: Player? = null
    private var player3: Player? = null

    @BeforeEach
    @Transactional
    open fun setUp() {
        val player1 = kalaPlayerRepository!!.save(Player(
            username ="player1"
        ))
        var player2 = kalaPlayerRepository.save(Player(
            username="player2")
                )
        player3 = kalaPlayerRepository.save(Player(
            username="player3"
        ))
        val boardPlayerOne = Board()
        val boardPlayerTwo = Board()
        val boardPlayerThree = Board()
        boardPlayerOne.playerOne= player1
        boardPlayerTwo.playerOne= player2
        boardPlayerThree.playerOne = player3
        boardPlayerThree.playerTwo = player2
        kalaBoardRepository!!.save(boardPlayerOne)
        kalaBoardRepository.save(boardPlayerTwo)
        kalaBoardRepository.save(boardPlayerThree)
    }

    @Test
    @Disabled
    fun testFindBoardsByPlayerOneEquals_whenPlayerOne_thenShowPlayerOneBoards() {
        val boardsByPlayerOneEquals = kalaBoardRepository!!.findBoardsByPlayerOneEquals(player1)
        assertThat(boardsByPlayerOneEquals).hasSize(1)
        assertThat(boardsByPlayerOneEquals[0]?.playerOne?.username).isEqualTo("player1")
    }

    @Test
    fun testFindBoardsByPlayerOneEquals_whenPlayerThree_thenShowPlayerThreeBoards() {
        val boardsByPlayerOneEquals = kalaBoardRepository!!.findBoardsByPlayerOneEquals(player3)
        assertThat(boardsByPlayerOneEquals).hasSize(1)
        assertThat(boardsByPlayerOneEquals[0]?.playerOne?.username).isEqualTo("player3")
    }

    @Test
    fun findBoardsByPlayerTwoIsNull_whenCalled_thenShowAllBoards() {
        val boardsByPlayerTwoIsNull = kalaBoardRepository!!.findBoardsByPlayerTwoIsNull()
        assertThat(boardsByPlayerTwoIsNull).hasSize(2)
        assertThat(boardsByPlayerTwoIsNull[0]?.playerOne?.username).isEqualTo("player1")
        assertThat(boardsByPlayerTwoIsNull[1]?.playerOne?.username).isEqualTo("player2")
    }
}