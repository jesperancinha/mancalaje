package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.Board;
import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_ACTIONS;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameActionsController.class)
@WithMockUser
public class GameActionsControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void pressHoleId() throws Exception {
        final Player testPlayer1 = new Player();
        final Player testPlayer2 = new Player();
        final BoardManager testBoardManager = BoardManager.create(testPlayer1, 1L, TEST_GAME_1);
        final Board testBoard = new Board(TEST_GAME_1);
        final Map<Long, BoardManager> boardManagerMap = new HashMap<>();
        testPlayer1.setEmail(TEST_FAKE_EMAIL);
        testPlayer1.setBoardManager(testBoardManager);
        testBoardManager.setBoard(testBoard);
        testBoard.setPlayer1(testPlayer1);
        testBoard.setPlayer2(testPlayer2);
        when(userManagerService.getSessionUser()).thenReturn(testPlayer1);
        doNothing().when(gameManagerService).swayStonesFromHole(testPlayer1, 1);
        doNothing().when(userService).refreshUser(TEST_FAKE_EMAIL);
        when(roomsManagerService.getBoardManagerMap()).thenReturn(boardManagerMap);

        mvc.perform(put(MANCALA_ACTIONS.concat("/nextMove/1"))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardManager.board.name").value(TEST_GAME_1));

        verify(userManagerService, only()).getSessionUser();
        verify(gameManagerService, only()).swayStonesFromHole(testPlayer1, 1);
        verify(userService, only()).refreshUser(TEST_FAKE_EMAIL);
        verify(roomsManagerService, only()).getBoardManagerMap();
    }
}