package org.jesperancinha.games.kalagameservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.exception.PlayerNotJoinedYetException
import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Pit
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPitRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository
import org.jesperancinha.games.kalagameservice.service.BoardService
import org.jesperancinha.games.kalagameservice.service.GameService
import org.jesperancinha.games.kalagameservice.service.PlayerService
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import javax.sql.DataSource

@WebMvcTest(controllers = [KalaGameController::class])
@MockBean(classes = [
    DataSource::class, JdbcUserDetailsManager::class, PasswordEncoder::class,
    KalaPlayerRepository::class, KalaPitRepository::class, KalaBoardRepository::class])
internal class KalaGameControllerTest {
    @MockBean
    private val gameService: GameService? = null

    @MockBean
    private val boardService: BoardService? = null

    @MockBean
    private val playerService: PlayerService? = null
    private val objectMapper = ObjectMapper()

    @Autowired
    private val mockMvc: MockMvc? = null

    @Test
    @WithMockUser("player1")
    @Throws(Exception::class)
    @Disabled
    fun testCreateBoard_whenRequest_thenBoardIsCreated() {
        val board = Board()
        board.id = 1L
        board.pits = mutableListOf()
        Mockito.`when`(gameService!!.createNewBoard(ArgumentMatchers.any())).thenReturn(board)
        val mvcResult = mockMvc!!.perform(MockMvcRequestBuilders.post("/api/create"))
            .andReturn()
        val contentAsString = mvcResult.response.contentAsString
        val (id) = objectMapper.readValue(contentAsString, BoardDto::class.java)
        Assertions.assertThat(id).isEqualTo(1L)
        Mockito.verify(playerService, Mockito.only())?.createOrFindPlayerByName("player1")
        Mockito.verify(gameService, Mockito.only()).createNewBoard(ArgumentMatchers.any())
    }

    @Test
    @WithMockUser("player1")
    @Throws(Throwable::class)
    @Disabled
    fun testMove_whenPlayer2NotJoined_thenFail() {
        val board = Board()
        board.id = 1L
        val pit = Pit()
        pit.id = 1L
        pit.stones = 10
        board.pits = listOf(pit)
        Mockito.`when`(boardService!!.findBoardById(1L)).thenReturn(board)
        Mockito.`when`(gameService!!.sowStonesFromPit(ArgumentMatchers.any(),
            ArgumentMatchers.any(),
            ArgumentMatchers.any())).thenThrow(
            PlayerNotJoinedYetException::class.java)
        mockMvc!!.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("NOT_JOINED"))
        Mockito.verify(playerService, Mockito.only())?.createOrFindPlayerByName("player1")
    }

    @Test
    @WithMockUser("player1")
    @Throws(Throwable::class)
    fun testMove_whenNoStones_thenFail() {
        val board = Board()
        board.id = 1L
        val pit = Pit()
        pit.id = 1L
        pit.stones = 0
        board.pits = listOf(pit)
        Mockito.`when`(boardService!!.findBoardById(1L)).thenReturn(board)
        mockMvc!!.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("ZERO_STONES"))
        Mockito.verify(playerService, Mockito.only())?.createOrFindPlayerByName("player1")
    }

    @Test
    @WithMockUser("player1")
    @Throws(Throwable::class)
    fun testMove_whenPitDoesntExist_thenFail() {
        val board = Board()
        board.id = 1L
        board.pits = mutableListOf()
        Mockito.`when`(boardService!!.findBoardById(1L)).thenReturn(board)
        mockMvc!!.perform(MockMvcRequestBuilders.put("/api/move/1/1"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andExpect(MockMvcResultMatchers.content().string("PIT_NOT_FOUND"))
        Mockito.verify(playerService, Mockito.only())?.createOrFindPlayerByName("player1")
    }
}