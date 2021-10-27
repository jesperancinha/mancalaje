package org.jesperancinha.games.kalagameservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.exception.PlayerNotJoinedYetException
import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Pit
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPitRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository
import org.jesperancinha.games.kalagameservice.service.BoardService
import org.jesperancinha.games.kalagameservice.service.GameService
import org.jesperancinha.games.kalagameservice.service.PlayerService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import javax.sql.DataSource

@WebMvcTest(controllers = [KalaGameController::class])
@MockkBean(
    classes = [
        DataSource::class, JdbcUserDetailsManager::class, PasswordEncoder::class,
        KalaPlayerRepository::class, KalaPitRepository::class, KalaBoardRepository::class]
)
internal class KalaGameControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {
    @MockkBean(relaxed = true)
    private lateinit var gameService: GameService

    @MockkBean(relaxed = true)
    private lateinit var boardService: BoardService

    @MockkBean(relaxed = true)
    private lateinit var playerService: PlayerService

    private val objectMapper = ObjectMapper()

    private val board = Board(playerOne = Player(), pitOne = Pit())

    @Test
    @WithMockUser("player1")
    fun testCreateBoard_whenRequest_thenBoardIsCreated() {
        board.id = 1L
        board.pits = mutableListOf()
        every { gameService.createNewBoard(any()) } returns board
        val mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/create"))
            .andReturn()
        val contentAsString = mvcResult.response.contentAsString
        val (id) = objectMapper.readValue(contentAsString, BoardDto::class.java)
        id shouldBe 1L
        verify(exactly = 1) { playerService.createOrFindPlayerByName("player1") }
        verify(exactly = 1) { gameService.createNewBoard(any()) }
    }

    @Test
    @WithMockUser("player1")
    fun testMove_whenPlayer2NotJoined_thenFail() {
        board.id = 1L
        val pit = Pit()
        pit.id = 1L
        pit.stones = 10
        board.pits = listOf(pit)
        every { boardService.findBoardById(1L) } returns board
        every { gameService.sowStonesFromPit(any(), any(), any()) } throws PlayerNotJoinedYetException()
        mockMvc.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("NOT_JOINED"))
        verify(exactly = 1) { playerService.createOrFindPlayerByName("player1") }
    }

    @Test
    @WithMockUser("player1")
    fun testMove_whenNoStones_thenFail() {
        board.id = 1L
        val pit = Pit()
        pit.id = 1L
        pit.stones = 0
        board.pits = listOf(pit)
        every { boardService.findBoardById(1L) } returns board
        mockMvc.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("ZERO_STONES"))
        verify(exactly = 1) { playerService.createOrFindPlayerByName("player1") }
    }

    @Test
    @WithMockUser("player1")
    fun testMove_whenPitDoesntExist_thenFail() {
        board.id = 1L
        board.pits = mutableListOf()
        every { boardService.findBoardById(1L) } returns board
        mockMvc.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("PIT_NOT_FOUND"))
        verify(exactly = 1) { playerService.createOrFindPlayerByName("player1") }
    }
}