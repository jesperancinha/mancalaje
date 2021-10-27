package org.jesperancinha.games.kalagameservice.service

import org.assertj.core.api.Assertions.assertThat
import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Pit
import org.jesperancinha.games.kalagameservice.model.PitType
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPitRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class GameServiceImplTest {
    @InjectMocks
    private val gameService: GameService? = null

    @Mock
    private val pitRepository: KalaPitRepository? = null

    @Mock
    private val boardRepository: KalaBoardRepository? = null

    @Mock
    private val playerRepository: KalaPlayerRepository? = null

    @Test
    fun testCreateNewBoard_whenCreateNewBoard_creationSuccessful() {
        Mockito.`when`(pitRepository!!.save(ArgumentMatchers.any(Pit::class.java)))
            .thenAnswer { invocationOnMock: InvocationOnMock -> invocationOnMock.getArgument(0) }
        Mockito.`when`(boardRepository!!.save(ArgumentMatchers.any(Board::class.java)))
            .thenAnswer { invocationOnMock: InvocationOnMock -> invocationOnMock.getArgument(0) }
        val user1 = Player(
            username = "user1"
        )
        val board = gameService!!.createNewBoard(user1)
        assertThat(board).isNotNull
        assertThat(board?.pits).isNotNull
        assertThat(board?.pits).hasSize(14)
        assertThat(board?.pits?.get(6)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board?.pits?.get(13)?.pitType).isEqualTo(PitType.LARGE)
        assertThat(board?.pits?.get(0)).isSameAs(board?.pits?.get(13)?.nextPit)
        var currentPit = board?.pits?.get(0)
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
        Mockito.verify(pitRepository, Mockito.times(28)).save(ArgumentMatchers.any())
        Mockito.verify(boardRepository, Mockito.times(1)).save(ArgumentMatchers.any())
        Mockito.verify(playerRepository, Mockito.times(1))?.save(ArgumentMatchers.any())
    }
}