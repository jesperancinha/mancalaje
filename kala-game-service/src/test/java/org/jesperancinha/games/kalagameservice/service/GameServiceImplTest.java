package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Pit;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository;
import org.jesperancinha.games.kalagameservice.repository.KalaPitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.games.kalagameservice.model.PitType.LARGE;
import static org.jesperancinha.games.kalagameservice.model.PitType.SMALL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private KalaPitRepository pitRepository;

    @Mock
    private KalaBoardRepository boardRepository;

    @Test
    void testCreateNewBoard_whenCreateNewBoard_creationSuccessful() {
        when(pitRepository.save(any(Pit.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(boardRepository.save(any(Board.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

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

        verify(pitRepository, times(14)).save(any());
        verify(boardRepository, times(1)).save(any());
    }
}