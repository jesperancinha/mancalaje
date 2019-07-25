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

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_ROOMS;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
@WithMockUser
public class RoomControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void joinGame() throws Exception {
        final Player testPlayer = new Player();
        testPlayer.setEmail(TEST_FAKE_EMAIL);
        final BoardManager testBoardManager = BoardManager.create(testPlayer, 1L, TEST_GAME_1);
        testBoardManager.getBoard().setPlayer1(testPlayer);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer);
        when(gameManagerService.joinPlayer(1L, testPlayer)).thenReturn(testBoardManager);

        mvc.perform(put(MANCALA_ROOMS.concat("/1"))
                .with(csrf())
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardManager.board.name").value(TEST_GAME_1))
                .andExpect(jsonPath("$.boardManager.board.player1.email").value(TEST_FAKE_EMAIL));

        verify(gameManagerService, only()).joinPlayer(1L, testPlayer);
        verify(userManagerService, only()).getSessionUser();
        verifyZeroInteractions(roomsManagerService);
    }

    @Test
    public void leaveGame() throws Exception {
        final Player testPlayer = new Player();
        testPlayer.setEmail(TEST_FAKE_EMAIL);
        final BoardManager testBoardManager = BoardManager.create(testPlayer, 1L, TEST_GAME_1);
        testBoardManager.getBoard().setPlayer1(testPlayer);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer);
        when(gameManagerService.leaveRoom(1L, TEST_FAKE_EMAIL)).thenReturn(testPlayer);

        mvc.perform(delete(MANCALA_ROOMS.concat("/1"))
                .with(csrf())
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        verify(gameManagerService, only()).leaveRoom(1L, TEST_FAKE_EMAIL);
        verify(userManagerService, times(1)).getSessionUser();
        verify(userManagerService, times(1)).setSessionUser(testPlayer);
        verifyZeroInteractions(roomsManagerService);
    }

    @Test
    public void leaveAllGames() throws Exception {
        final Player testPlayer = new Player();
        testPlayer.setEmail(TEST_FAKE_EMAIL);
        final BoardManager testBoardManager = BoardManager.create(testPlayer, 1L, TEST_GAME_1);
        testBoardManager.getBoard().setPlayer1(testPlayer);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer);
        doNothing().when(gameManagerService).leaveAllRooms(TEST_FAKE_EMAIL);

        mvc.perform(delete(MANCALA_ROOMS)
                .with(csrf())
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        verify(gameManagerService, only()).leaveAllRooms(TEST_FAKE_EMAIL);
        verify(userManagerService, only()).getSessionUser();
        verifyZeroInteractions(roomsManagerService);
    }
}