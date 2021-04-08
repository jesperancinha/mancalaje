package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.model.Player;
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository;
import org.jesperancinha.games.kalagameservice.repository.KalaPitRepository;
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jesperancinha.games.kalagameservice.model.PitType.LARGE;
import static org.jesperancinha.games.kalagameservice.model.PitType.SMALL;


@SpringBootTest
@Transactional
class GameServiceImplItTest {

    @Autowired
    private GameServiceImpl gameService;

    @Autowired
    private KalaPlayerRepository kalaPlayerRepository;
    @Autowired
    private KalaPitRepository kalaPitRepository;
    @Autowired
    private KalaBoardRepository kalaBoardRepository;

    @Test
    void testCreateNewBoard_whenCreateNewBoard_creationSuccessful() {
        final var user1 = Player.builder().username("user1").build();
        kalaPlayerRepository.save(user1);
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

    @Test
    void testSowStonesFromPit_whenLandingOnKalah_thenAnotherTurn() {
        final var user1 = Player.builder().username("user1").build();
        final var user2 = Player.builder().username("user2").build();
        kalaPlayerRepository.save(user1);
        kalaPlayerRepository.save(user2);
        user1.setOpponent(user2);
        user2.setOpponent(user1);
        kalaPlayerRepository.save(user1);
        kalaPlayerRepository.save(user2);
        var board = gameService.createNewBoard(user1);

        board = gameService.sowStonesFromPit(user1, board.getPitOne(), board);
        assertThat(board).isNotNull();
        assertThat(board.getPits()).isNotNull();
        assertThat(board.getPits()).hasSize(14);
        assertThat(board.getPits().get(6).getPitType()).isEqualTo(LARGE);
        assertThat(board.getPits().get(13).getPitType()).isEqualTo(LARGE);
        assertThat(board.getPits().get(0)).isSameAs(board.getPits().get(13).getNextPit());

        var currentPit = board.getPits().get(0);
        assertThat(currentPit.getStones()).isEqualTo(0);
        assertThat(currentPit.getPlayer()).isSameAs(user1);
        assertThat(currentPit.getPitType()).isEqualTo(SMALL);
        currentPit = currentPit.getNextPit();
        for (int i = 1; i < 6; i++) {
            assertThat(currentPit.getStones()).isEqualTo(7);
            assertThat(currentPit.getPlayer()).isSameAs(user1);
            assertThat(currentPit.getPitType()).isEqualTo(SMALL);
            currentPit = currentPit.getNextPit();
        }
        assertThat(currentPit.getStones()).isEqualTo(1);
        assertThat(currentPit.getPlayer()).isSameAs(user1);
        currentPit = currentPit.getNextPit();
        for (int i = 0; i < 6; i++) {
            assertThat(currentPit.getStones()).isEqualTo(6);
            assertThat(currentPit.getPlayer()).isNull();
            currentPit = currentPit.getNextPit();
        }
        assertThat(currentPit.getStones()).isEqualTo(0);
        assertThat(board.getCurrentPlayer()).isEqualTo(user1);
    }

    @Test
    void testSowStonesFromPit_whenLandingOnOpponent_thenLoseTurn() {
        var user1 = Player.builder().username("user1").build();
        var user2 = Player.builder().username("user2").build();
        kalaPlayerRepository.save(user1);
        kalaPlayerRepository.save(user2);
        user1.setOpponent(user2);
        user2.setOpponent(user1);
        user1 = kalaPlayerRepository.save(user1);
        user2 = kalaPlayerRepository.save(user2);
        var board = gameService.createNewBoard(user1);
        board = gameService.joinPlayer(user2, board);
        board = gameService.sowStonesFromPit(user1, board.getPits().get(3), board);
        assertThat(board).isNotNull();
        assertThat(board.getPits()).isNotNull();
        assertThat(board.getPits()).hasSize(14);
        assertThat(board.getPits().get(6).getPitType()).isEqualTo(LARGE);
        assertThat(board.getPits().get(13).getPitType()).isEqualTo(LARGE);
        assertThat(board.getPits().get(0)).isSameAs(board.getPits().get(13).getNextPit());

        var currentPit = board.getPits().get(0);
        for (int i = 0; i < 3; i++) {
            assertThat(currentPit.getStones()).isEqualTo(6);
            assertThat(currentPit.getPlayer().getUsername()).isEqualTo(user1.getUsername());
            assertThat(currentPit.getPitType()).isEqualTo(SMALL);
            currentPit = currentPit.getNextPit();
        }
        assertThat(currentPit.getStones()).isEqualTo(0);
        assertThat(currentPit.getPlayer().getUsername()).isEqualTo(user1.getUsername());
        assertThat(currentPit.getPitType()).isEqualTo(SMALL);
        currentPit = currentPit.getNextPit();

        for (int i = 4; i < 6; i++) {
            assertThat(currentPit.getStones()).isEqualTo(7);
            assertThat(currentPit.getPlayer().getUsername()).isEqualTo(user1.getUsername());
            assertThat(currentPit.getPitType()).isEqualTo(SMALL);
            currentPit = currentPit.getNextPit();
        }
        assertThat(currentPit.getStones()).isEqualTo(1);
        assertThat(currentPit.getPlayer().getUsername()).isEqualTo(user1.getUsername());
        currentPit = currentPit.getNextPit();
        for (int i = 0; i < 3; i++) {
            assertThat(currentPit.getStones()).isEqualTo(7);
            assertThat(currentPit.getPlayer().getUsername()).isEqualTo(user2.getUsername());
            currentPit = currentPit.getNextPit();
        }
        for (int i = 3; i < 6; i++) {
            assertThat(currentPit.getStones()).isEqualTo(6);
            assertThat(currentPit.getPlayer().getUsername()).isEqualTo(user2.getUsername());
            currentPit = currentPit.getNextPit();
        }
        assertThat(currentPit.getStones()).isEqualTo(0);
        assertThat(board.getCurrentPlayer().getUsername()).isEqualTo(user2.getUsername());
    }
}