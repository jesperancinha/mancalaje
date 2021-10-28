package org.jesperancinha.games.kalagameservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.exception.PlayerNotJoinedYetException
import org.jesperancinha.games.kalagameservice.model.KalahBoard
import org.jesperancinha.games.kalagameservice.model.KalahWasher
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalahBoardRepository
import org.jesperancinha.games.kalagameservice.repository.KalahCupRepository
import org.jesperancinha.games.kalagameservice.repository.KalahPlayerRepository
import org.jesperancinha.games.kalagameservice.repository.KalahTableRepository
import org.jesperancinha.games.kalagameservice.repository.KalahWasherRepository
import org.jesperancinha.games.kalagameservice.service.BoardService
import org.jesperancinha.games.kalagameservice.service.GameService
import org.jesperancinha.games.kalagameservice.service.PlayerService
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [KalaGameController::class])
class KalaGameControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {
    @MockkBean(relaxed = true)
    private lateinit var gameService: GameService

    @MockkBean(relaxed = true)
    private lateinit var boardService: BoardService

    @MockkBean(relaxed = true)
    private lateinit var playerService: PlayerService

    @MockkBean(relaxed = true)
    private lateinit var jdbcUserDetailsManager: JdbcUserDetailsManager

    @MockkBean(relaxed = true)
    private lateinit var passwordEncoder: PasswordEncoder

    @MockkBean(relaxed = true)
    private lateinit var kalahWasherRepository: KalahWasherRepository

    @MockkBean(relaxed = true)
    private lateinit var kalahTableRepository: KalahTableRepository

    @MockkBean(relaxed = true)
    private lateinit var kalahBoardRepository: KalahBoardRepository

    @MockkBean(relaxed = true)
    private lateinit var kalahCupRepository: KalahCupRepository

    @MockkBean(relaxed = true)
    private lateinit var kalahPlayerRepository: KalahPlayerRepository

    private val objectMapper = ObjectMapper()

    private val kalahBoard = KalahBoard(owner = Player(username = "Joao"))

    @Test
    @WithMockUser("player1")
    fun testCreateBoard_whenRequest_thenBoardIsCreated() {
        kalahBoard.id = 1L
        kalahBoard.kalahWashers = mutableListOf()
        every { gameService.createNewBoard(any()) } returns kalahBoard
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
    @Disabled
    fun testMove_whenPlayer2NotJoined_thenFail() {
        kalahBoard.id = 1L
        val kalahWasher = KalahWasher()
        kalahWasher.id = 1L
//        kalahWasher.stones = 10
        kalahBoard.kalahWashers = listOf(kalahWasher)
        every { boardService.findBoardById(1L) } returns kalahBoard
        every { gameService.sowStonesFromPit(any(), any(), any()) } throws PlayerNotJoinedYetException()
        mockMvc.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("NOT_JOINED"))
        verify(exactly = 1) { playerService.createOrFindPlayerByName("player1") }
    }

    @Test
    @WithMockUser("player1")
    @Disabled
    fun testMove_whenNoStones_thenFail() {
        kalahBoard.id = 1L
        val kalahWasher = KalahWasher()
        kalahWasher.id = 1L
//        kalahWasher.stones = 0
        kalahBoard.kalahWashers = listOf(kalahWasher)
        every { boardService.findBoardById(1L) } returns kalahBoard
        mockMvc.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("ZERO_STONES"))
        verify(exactly = 1) { playerService.createOrFindPlayerByName("player1") }
    }

    @Test
    @WithMockUser("player1")
    fun testMove_whenPitDoesntExist_thenFail() {
        kalahBoard.id = 1L
        kalahBoard.kalahWashers = mutableListOf()
        every { boardService.findBoardById(1L) } returns kalahBoard
        mockMvc.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("PIT_NOT_FOUND"))
        verify(exactly = 1) { playerService.createOrFindPlayerByName("player1") }
    }
}