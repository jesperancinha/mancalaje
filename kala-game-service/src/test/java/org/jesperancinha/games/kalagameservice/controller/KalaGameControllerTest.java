package org.jesperancinha.games.kalagameservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.games.kalagameservice.dto.BoardDto;
import org.jesperancinha.games.kalagameservice.exception.PlayerNotJoinedYetException;
import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Pit;
import org.jesperancinha.games.kalagameservice.service.BoardService;
import org.jesperancinha.games.kalagameservice.service.GameService;
import org.jesperancinha.games.kalagameservice.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = KalaGameController.class)
@MockBean(classes = {DataSource.class, JdbcUserDetailsManager.class, PasswordEncoder.class})
class KalaGameControllerTest {

    @MockBean
    private GameService gameService;

    @MockBean
    private BoardService boardService;

    @MockBean
    private PlayerService playerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser("player1")
    void testCreateBoard_whenRequest_thenBoardIsCreated() throws Exception {
        final Board board = new Board();
        board.setId(1L);
        board.setPits(new ArrayList<>());
        when(gameService.createNewBoard(any())).thenReturn(board);
        final MvcResult mvcResult = mockMvc.perform(post("/api/create"))
                .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final BoardDto boardDto = objectMapper.readValue(contentAsString, BoardDto.class);

        assertThat(boardDto.getId()).isEqualTo(1L);

        verify(playerService, only()).createOrFindPlayerByName("player1");
        verify(gameService, only()).createNewBoard(any());

    }

    @Test
    @WithMockUser("player1")
    void testMove_whenPlayer2NotJoined_thenFail() throws Throwable {
        final Board board = new Board();
        board.setId(1L);
        final Pit pit = new Pit();
        pit.setId(1L);
        pit.setStones(10);
        board.setPits(Collections.singletonList(pit));
        when(boardService.findBoardById(1L)).thenReturn(board);
        when(gameService.sowStonesFromPit(any(), any(), any())).thenThrow(PlayerNotJoinedYetException.class);
        mockMvc.perform(put("/api/move/1/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("NOT_JOINED"));

        verify(playerService, only()).createOrFindPlayerByName("player1");

    }

    @Test
    @WithMockUser("player1")
    void testMove_whenNoStones_thenFail() throws Throwable {
        final Board board = new Board();
        board.setId(1L);
        final Pit pit = new Pit();
        pit.setId(1L);
        pit.setStones(0);
        board.setPits(Collections.singletonList(pit));
        when(boardService.findBoardById(1L)).thenReturn(board);
        mockMvc.perform(put("/api/move/1/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("ZERO_STONES"));

        verify(playerService, only()).createOrFindPlayerByName("player1");

    }

    @Test
    @WithMockUser("player1")
    void testMove_whenPitDoesntExist_thenFail() throws Throwable {
        final Board board = new Board();
        board.setId(1L);
        board.setPits(new ArrayList<>());
        when(boardService.findBoardById(1L)).thenReturn(board);
        mockMvc.perform(put("/api/move/1/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("PIT_NOT_FOUND"));

        verify(playerService, only()).createOrFindPlayerByName("player1");

    }
}