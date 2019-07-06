package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.game.RoomsManager;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class GameManagerServiceTest {

    @InjectMocks
    private GameManagerService gameManagerService;

    @Test
    @Ignore
    public void createBoard() {
        Player mockPlayer = mock(Player.class);
        gameManagerService.createBoard(mockPlayer, "test");

        RoomsManager roomsManager = gameManagerService.listAllGames();

        List<BoardManager> boardManagers = roomsManager.getBoardManagers();
        assertThat(boardManagers).hasSize(1);
        Optional<BoardManager> first = boardManagers.stream().findFirst();
        assertThat(first.isPresent()).isTrue();
        BoardManager boardManager = first.orElse(null);
        assertThat(boardManager).isNotNull();
        assertThat(boardManager.getBoardManagerId()).isEqualTo(1);
    }
}