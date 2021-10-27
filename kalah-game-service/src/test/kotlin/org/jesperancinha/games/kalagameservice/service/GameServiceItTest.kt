package org.jesperancinha.games.kalagameservice.service

import org.assertj.core.api.Assertions.assertThat
import org.jesperancinha.games.kalagameservice.model.PitType
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@Transactional
internal open class GameServiceItTest(
    @Autowired
    private val gameService: GameService,
    @Autowired
    private val kalaPlayerRepository: KalaPlayerRepository
) {


    @Test
    fun testCreateNewBoard_whenCreateNewBoard_creationSuccessful() {
        val user1 = Player(
            username = "user1"
        )
        kalaPlayerRepository.save(user1)
        val board = gameService.createNewBoard(user1)
        assertThat(board).isNotNull
        assertThat(board.pits).isNotNull
        assertThat(board.pits).hasSize(14)
        assertThat(board.pits?.get(6)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board.pits?.get(13)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board.pits?.get(0)).isSameAs(board.pits?.get(13)?.nextPit)
        var currentPit = board.pits?.get(0)
        for (i in 0..5) {
            assertThat(currentPit?.stones).isEqualTo(6)
            assertThat(currentPit?.player).isSameAs(user1)
            assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
            currentPit = currentPit?.nextPit
        }
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(currentPit?.player).isSameAs(user1)
        currentPit = currentPit?.nextPit
        for (i in 0..5) {
            assertThat(currentPit?.stones).isEqualTo(6)
            assertThat(currentPit?.player).isNull()
            currentPit = currentPit?.nextPit
        }
        assertThat(currentPit?.stones).isEqualTo(0)
    }

    @Test
    fun testSowStonesFromPit_whenLandingOnKalah_thenAnotherTurn() {
        val user1 = Player(
            username = "user1"
        )
        val user2 = Player(
            username = "user2"
        )
        kalaPlayerRepository.save(user1)
        kalaPlayerRepository.save(user2)
        user1.opponent = user2
        user2.opponent = user1
        kalaPlayerRepository.save(user1)
        kalaPlayerRepository.save(user2)
        var board = gameService.createNewBoard(user1)
        board = gameService.joinPlayer(user2, board)
        board = gameService.sowStonesFromPit(user1,  board.pitOne, board)
        assertThat(board).isNotNull
        assertThat(board.pits).isNotNull
        assertThat(board.pits).hasSize(14)
        assertThat(board.pits?.get(6)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board.pits?.get(13)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board.pits?.get(0)).isSameAs(board.pits?.get(13)?.nextPit)
        var currentPit = board.pits?.get(0)
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(currentPit?.player).isSameAs(user1)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        for (i in 1..5) {
            assertThat(currentPit?.stones).isEqualTo(7)
            assertThat(currentPit?.player).isSameAs(user1)
            assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
            currentPit = currentPit?.nextPit
        }
        assertThat(currentPit?.stones).isEqualTo(1)
        assertThat(currentPit?.player).isSameAs(user1)
        currentPit = currentPit?.nextPit
        for (i in 0..5) {
            assertThat(currentPit?.stones).isEqualTo(6)
            assertThat(currentPit?.player).isSameAs(user2)
            currentPit = currentPit?.nextPit
        }
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(board?.currentPlayer).isEqualTo(user1)
    }

    @Test
    fun testSowStonesFromPit_whenLandingOnOpponent_thenLoseTurn() {
        var user1 = Player(
            username = "user1"
        )
        var user2 = Player(
            username = "user2"
        )
        kalaPlayerRepository!!.save<Player>(user1)
        kalaPlayerRepository.save<Player>(user2)
        user1.opponent = user2
        user2.opponent = user1
        user1 = kalaPlayerRepository.save<Player>(user1)
        user2 = kalaPlayerRepository.save<Player>(user2)
        var board = gameService.createNewBoard(user1)
        board = gameService.joinPlayer(user2, board)
        board = gameService.sowStonesFromPit(user1, board.pits?.get(3), board)
        assertThat(board).isNotNull
        assertThat(board?.pits).isNotNull
        assertThat(board?.pits).hasSize(14)
        assertThat(board?.pits?.get(6)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board?.pits?.get(13)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board?.pits?.get(0)).isSameAs(board?.pits?.get(13)?.nextPit)
        var currentPit = board?.pits?.get(0)
        for (i in 0..2) {
            assertThat(currentPit?.stones).isEqualTo(6)
            assertThat(currentPit?.player?.username).isEqualTo(user1.username)
            assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
            currentPit = currentPit?.nextPit
        }
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(currentPit?.player?.username).isEqualTo(user1.username)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        for (i in 4..5) {
            assertThat(currentPit?.stones).isEqualTo(7)
            assertThat(currentPit?.player?.username).isEqualTo(user1.username)
            assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
            currentPit = currentPit?.nextPit
        }
        assertThat(currentPit?.stones).isEqualTo(1)
        assertThat(currentPit?.player?.username).isEqualTo(user1.username)
        currentPit = currentPit?.nextPit
        for (i in 0..2) {
            assertThat(currentPit?.stones).isEqualTo(7)
            assertThat(currentPit?.player?.username).isEqualTo(user2.username)
            currentPit = currentPit?.nextPit
        }
        for (i in 3..5) {
            assertThat(currentPit?.stones).isEqualTo(6)
            assertThat(currentPit?.player?.username).isEqualTo(user2.username)
            currentPit = currentPit?.nextPit
        }
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(board?.currentPlayer?.username).isEqualTo(user2.username)
    }

    @Test
    fun testSowStonesFromPit_whenLandingOnOwnedPit_thenPickAllAndAddToKalah() {
        val user1 = Player(
            username = "user1"
        )
        val user2 = Player(
            username = "user2"
        )
        kalaPlayerRepository.save(user1)
        kalaPlayerRepository.save(user2)
        user1.opponent = user2
        user2.opponent = user1
        kalaPlayerRepository.save(user1)
        kalaPlayerRepository.save(user2)
        var board = gameService.createNewBoard(user1)
        board = gameService.joinPlayer(user2, board)
        board = board.pitOne.let { gameService.sowStonesFromPit(user1, it, board) }
        board = board.pitOne.nextPit.let { gameService.sowStonesFromPit(user1, it, board) }
        board = board.pitTwo.let { gameService.sowStonesFromPit(user2, it, board) }
        board = board.pitOne.nextPit?.nextPit?.nextPit?.nextPit?.nextPit.let {
            gameService.sowStonesFromPit(
                user1,
                it,
                board
            )
        }
        assertThat(board).isNotNull
        assertThat(board.pits).isNotNull
        assertThat(board.pits).hasSize(14)
        assertThat(board.pits?.get(6)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board.pits?.get(13)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board.pits?.get(0)).isSameAs(board.pits?.get(13)?.nextPit)
        var currentPit = board.pits?.get(0)
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(currentPit?.player).isSameAs(user1)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(currentPit?.player).isSameAs(user1)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(8)
        assertThat(currentPit?.player).isSameAs(user1)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(8)
        assertThat(currentPit?.player).isSameAs(user1)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(8)
        assertThat(currentPit?.player).isSameAs(user1)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(currentPit?.player).isSameAs(user1)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(6)
        assertThat(currentPit?.player).isSameAs(user1)
        assertThat(currentPit?.pitType).isEqualTo(PitType.LARGE)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(0)
        assertThat(currentPit?.player).isSameAs(user2)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(9)
        assertThat(currentPit?.player).isSameAs(user2)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(8)
        assertThat(currentPit?.player).isSameAs(user2)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(8)
        assertThat(currentPit?.player).isSameAs(user2)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(8)
        assertThat(currentPit?.player).isSameAs(user2)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(8)
        assertThat(currentPit?.player).isSameAs(user2)
        assertThat(currentPit?.pitType).isEqualTo(PitType.SMALL)
        currentPit = currentPit?.nextPit
        assertThat(currentPit?.stones).isEqualTo(1)
        assertThat(currentPit?.player).isSameAs(user2)
        assertThat(currentPit?.pitType).isEqualTo(PitType.LARGE)
    }
}