package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_BOARDS;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardsController.class)
@WithMockUser
public class BoardsControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void createGame() throws Exception {
        final Player testPlayer = new Player();
        final BoardManager testBoardManager = BoardManager.create(testPlayer, 1L, TEST_GAME_1);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer);
        when(gameManagerService.createBoard(testPlayer, TEST_GAME_1)).thenReturn(testBoardManager);

        mvc.perform(post(MANCALA_BOARDS)
                .with(csrf())
                .content("{\"boardName\":\"game1\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardManager.board.name").value(TEST_GAME_1));

        verify(userManagerService, only()).getSessionUser();
        verify(gameManagerService, only()).createBoard(testPlayer, TEST_GAME_1);
        verifyZeroInteractions(roomsManagerService);
    }

    @Test
    public void getGame() throws Exception {
        final Player testPlayer = new Player();
        testPlayer.setEmail(TEST_FAKE_EMAIL);
        final BoardManager testBoardManager = BoardManager.create(testPlayer, 1L, TEST_GAME_1);
        final Map<Long, BoardManager> boardManagerMap = new HashMap<>();
        testBoardManager.getBoard().setPlayer1(testPlayer);
        doNothing().when(userManagerService).setSessionUser(testPlayer);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer);
        when(gameManagerService.handleBoardManager(1L)).thenReturn(testBoardManager);
        when(roomsManagerService.getBoardManagerMap()).thenReturn(boardManagerMap);

        mvc.perform(get(MANCALA_BOARDS.concat("/1"))
                .with(csrf())
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardManager.board.name").value(TEST_GAME_1))
                .andExpect(jsonPath("$.boardManager.board.player1.email").value(TEST_FAKE_EMAIL));

        verify(gameManagerService, only()).handleBoardManager(1L);
        verify(userManagerService, times(1)).getSessionUser();
        verify(userManagerService, times(1)).setSessionUser(testPlayer);
        verify(roomsManagerService, only()).getBoardManagerMap();
    }

    @Test
    public void removeRoom() throws Exception {
        final Player testPlayer = new Player();
        testPlayer.setEmail(TEST_FAKE_EMAIL);
        final BoardManager testBoardManager = BoardManager.create(testPlayer, 1L, TEST_GAME_1);
        testBoardManager.getBoard().setPlayer1(testPlayer);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer);
        when(gameManagerService.removeRoom(1L, testPlayer)).thenReturn(testBoardManager);

        mvc.perform(delete(MANCALA_BOARDS.concat("/1"))
                .with(csrf())
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardManager.board.name").value(TEST_GAME_1))
                .andExpect(jsonPath("$.boardManager.board.player1.email").value(TEST_FAKE_EMAIL));

        verify(gameManagerService, only()).removeRoom(1L, testPlayer);
        verify(userManagerService, only()).getSessionUser();
        verifyZeroInteractions(roomsManagerService);
    }

    @Test
    public void listCurrentGame() throws Exception {
        final Player testPlayer = new Player();
        testPlayer.setEmail(TEST_FAKE_EMAIL);
        final BoardManager testBoardManager = BoardManager.create(testPlayer, 1L, TEST_GAME_1);
        testBoardManager.getBoard().setPlayer1(testPlayer);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer);
        when(gameManagerService.listPlayerGame(testPlayer)).thenReturn(testBoardManager);

        mvc.perform(get(MANCALA_BOARDS)
                .with(csrf())
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardManager.board.name").value(TEST_GAME_1))
                .andExpect(jsonPath("$.boardManager.board.player1.email").value(TEST_FAKE_EMAIL));

        verify(gameManagerService, only()).listPlayerGame(testPlayer);
        verify(userManagerService, only()).getSessionUser();
        verifyZeroInteractions(roomsManagerService);
    }

    @Test
    public void listAllCurrentGames() throws Exception {
        final Player testPlayer = new Player();
        testPlayer.setEmail(TEST_FAKE_EMAIL);
        final BoardManager testBoardManager = BoardManager.create(testPlayer, 1L, TEST_GAME_1);
        testBoardManager.getBoard().setPlayer1(testPlayer);
        final List<BoardManager> allGames = Collections.singletonList(testBoardManager);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer);
        when(gameManagerService.listAllGames()).thenReturn(allGames);

        mvc.perform(get(MANCALA_BOARDS.concat("/all"))
                .with(csrf())
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].boardManager.board.name").value(TEST_GAME_1))
                .andExpect(jsonPath("$[0].boardManager.board.player1.email").value(TEST_FAKE_EMAIL));

        verify(userManagerService, only()).getSessionUser();
        verify(gameManagerService, only()).listAllGames();
        verifyZeroInteractions(roomsManagerService);
    }
}