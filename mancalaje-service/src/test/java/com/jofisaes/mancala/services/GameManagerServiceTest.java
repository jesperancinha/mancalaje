package com.jofisaes.mancala.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class GameManagerServiceTest {

    @InjectMocks
    private GameManagerService gameManagerService;

    @Test
    public void createBoard() {
        Player mockPlayer = mock(Player.class);
        gameManagerService.createBoard(mockPlayer);

        Collection<BoardManager> boardManagers = gameManagerService.listAllGames();

        assertThat(boardManagers).hasSize(1);
        Optional<BoardManager> first = boardManagers.stream().findFirst();
        assertThat(first.isPresent()).isTrue();
        BoardManager boardManager = first.orElse(null);
        assertThat(boardManager).isNotNull();
        assertThat(boardManager.getBoardManagerId()).isEqualTo(1);
    }
}