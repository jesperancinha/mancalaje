package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.repository.UserRepository;
import com.jofisaes.mancala.services.authentication.DefaultUserDetailsService;
import com.jofisaes.mancala.services.game.GameManagerService;
import com.jofisaes.mancala.services.room.RoomsManagerService;
import com.jofisaes.mancala.services.user.UserManagerService;
import com.jofisaes.mancala.services.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.jofisaes.mancala.rest.mappings.Mappings.MANCALA_BOARDS;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardsController.class)
@WithMockUser
public class BoardsControllerTest {

    private static final String TEST_GAME_1 = "game1";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserManagerService userManagerService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GameManagerService gameManagerService;

    @MockBean
    private RoomsManagerService roomsManagerService;

    @MockBean
    private AuthenticationProvider authenticationProvider;

    @MockBean
    private DefaultUserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void createGame() throws Exception {
        final Player mockPlayer = new Player();
        final BoardManager boardManager = BoardManager.create(mockPlayer, 1L, TEST_GAME_1);
        when(userManagerService.getSessionUser()).thenReturn(mockPlayer);
        when(gameManagerService.createBoard(mockPlayer, TEST_GAME_1)).thenReturn(boardManager);

        mvc.perform(MockMvcRequestBuilders
                .post(MANCALA_BOARDS)
                .with(csrf())
                .content("{\"boardName\":\"game1\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.boardManager.board.name").value(TEST_GAME_1));

        verifyZeroInteractions(passwordEncoder);
        verifyZeroInteractions(userDetailsService);
        verifyZeroInteractions(authenticationProvider);
        verifyZeroInteractions(passwordEncoder);
        verifyZeroInteractions(roomsManagerService);
        verifyZeroInteractions(userRepository);
        verifyZeroInteractions(userService);
        verify(userManagerService, only()).getSessionUser();
        verify(gameManagerService, only()).createBoard(mockPlayer, TEST_GAME_1);
    }

    @Test
    public void getGame() {
    }

    @Test
    public void removeRoom() {
    }

    @Test
    public void listCurrentGame() {
    }

    @Test
    public void listAllCurrentGames() {
    }
}