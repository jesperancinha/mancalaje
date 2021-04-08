package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.games.kalagameservice.model.PitType.LARGE;

class GameServiceImplTest {

    private final GameService gameService = new GameServiceImpl();

    @Test
    void testCreateNewBoard_whenCreateNewBoard_creationSuccessful() {
        final Board board = gameService.createNewBoard(Player.builder().username("user1").build());

        assertThat(board).isNotNull();
        assertThat(board.getPits()).isNotNull();
        assertThat(board.getPits()).hasSize(14);
        assertThat(board.getPits().get(6).getPitType()).isEqualTo(LARGE);
        assertThat(board.getPits().get(13).getPitType()).isEqualTo(LARGE);
    }
}