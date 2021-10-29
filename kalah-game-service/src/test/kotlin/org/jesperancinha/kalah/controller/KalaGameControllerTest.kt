package org.jesperancinha.kalah.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.kalah.dto.BoardDto
import org.jesperancinha.kalah.exception.PlayerNotJoinedYetException
import org.jesperancinha.kalah.model.KalahBoard
import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.model.Player
import org.jesperancinha.kalah.repository.KalahBoardRepository
import org.jesperancinha.kalah.repository.KalahCupRepository
import org.jesperancinha.kalah.repository.KalahPlayerRepository
import org.jesperancinha.kalah.repository.KalahTableRepository
import org.jesperancinha.kalah.repository.KalahWasherRepository
import org.jesperancinha.kalah.service.KalahBoardService
import org.jesperancinha.kalah.service.KalahGameService
import org.jesperancinha.kalah.service.KalahPlayerService
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
    private lateinit var gameService: KalahGameService

    @MockkBean(relaxed = true)
    private lateinit var boardService: KalahBoardService

    @MockkBean(relaxed = true)
    private lateinit var playerService: KalahPlayerService

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
        every { gameService.rolloutCupsFromPayersWasherOnBoard(any(), any(), any()) } throws PlayerNotJoinedYetException()
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