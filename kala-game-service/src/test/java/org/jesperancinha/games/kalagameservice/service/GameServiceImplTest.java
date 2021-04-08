package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.games.kalagameservice.model.PitType.LARGE;
import static org.jesperancinha.games.kalagameservice.model.PitType.SMALL;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    private final GameService gameService = new GameServiceImpl();

    @Test
    void testCreateNewBoard_whenCreateNewBoard_creationSuccessful() {
        final var user1 = Player.builder().username("user1").build();
        final var board = gameService.createNewBoard(user1);

        assertThat(board).isNotNull();
        assertThat(board.getPits()).isNotNull();
        assertThat(board.getPits()).hasSize(14);
        assertThat(board.getPits().get(6).getPitType()).isEqualTo(LARGE);
        assertThat(board.getPits().get(13).getPitType()).isEqualTo(LARGE);
        assertThat(board.getPits().get(0)).isSameAs(board.getPits().get(13).getNextPit());

        var currentPit = board.getPits().get(0);
        for (int i = 0; i < 6; i++) {
            assertThat(currentPit.getStones()).isEqualTo(6);
            assertThat(currentPit.getPlayer()).isSameAs(user1);
            assertThat(currentPit.getPitType()).isEqualTo(SMALL);
            currentPit = currentPit.getNextPit();
        }
        assertThat(currentPit.getStones()).isEqualTo(0);
        assertThat(currentPit.getPlayer()).isSameAs(user1);
        currentPit = currentPit.getNextPit();
        for (int i = 0; i < 6; i++) {
            assertThat(currentPit.getStones()).isEqualTo(6);
            assertThat(currentPit.getPlayer()).isNull();
            currentPit = currentPit.getNextPit();
        }
        assertThat(currentPit.getStones()).isEqualTo(0);
    }
}