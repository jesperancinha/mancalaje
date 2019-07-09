package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class GameManagerServiceTest {

    private GameManagerService gameManagerService = new GameManagerService(20, new RoomsManager());

    @Test
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
        assertThat(boardManager.getOwner()).isSameAs(mockPlayer);
        assertThat(boardManager.getBoard().getPlayer1()).isNull();
        assertThat(boardManager.getBoard().getPlayer2()).isNull();
    }
}