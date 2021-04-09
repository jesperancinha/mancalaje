package org.jesperancinha.games.kalagameservice.service;

import org.jesperancinha.games.kalagameservice.exception.InvalidPitException;
import org.jesperancinha.games.kalagameservice.exception.NotOwnedPitException;
import org.jesperancinha.games.kalagameservice.exception.WrongTurnException;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class GameServiceImplExceptionItTest {

    @Autowired
    private GameServiceImpl gameService;

    @Autowired
    private KalaPlayerRepository kalaPlayerRepository;

    @Test
    void testSowStonesFromPit_whenTryingToSowInAWrongTurn_thenFail() {
        final var user1 = kalaPlayerRepository.save(Player.builder().username("user1").build());
        final var user2 = kalaPlayerRepository.save(Player.builder().username("user2").build());
        user1.setOpponent(user2);
        user2.setOpponent(user1);
        final var userFinal1 = kalaPlayerRepository.save(user1);
        final var userFinal2 = kalaPlayerRepository.save(user2);
        final var board = gameService.createNewBoard(userFinal1);
        final var boardFinal = gameService.joinPlayer(userFinal2, board);

        assertThrows(WrongTurnException.class, () -> gameService.sowStonesFromPit(userFinal2, board.getPitTwo(), boardFinal));
    }

    @Test
    void testSowStonesFromPit_whenTryingToSowInAWrongPit_thenFail() {
        final var user1 = kalaPlayerRepository.save(Player.builder().username("user1").build());
        final var user2 = kalaPlayerRepository.save(Player.builder().username("user2").build());
        user1.setOpponent(user2);
        user2.setOpponent(user1);
        final var userFinal1 = kalaPlayerRepository.save(user1);
        final var userFinal2 = kalaPlayerRepository.save(user2);
        final var board = gameService.createNewBoard(userFinal1);
        final var boardFinal = gameService.joinPlayer(userFinal2, board);

        assertThrows(NotOwnedPitException.class, () -> gameService.sowStonesFromPit(userFinal1, board.getPitTwo(), boardFinal));
    }

    @Test
    void testSowStonesFromPit_whenTryingToSowFromAKalah_thenFail() {
        final var user1 = kalaPlayerRepository.save(Player.builder().username("user1").build());
        final var user2 = kalaPlayerRepository.save(Player.builder().username("user2").build());
        user1.setOpponent(user2);
        user2.setOpponent(user1);
        final var userFinal1 = kalaPlayerRepository.save(user1);
        final var userFinal2 = kalaPlayerRepository.save(user2);
        final var board = gameService.createNewBoard(userFinal1);
        final var boardFinal = gameService.joinPlayer(userFinal2, board);

        assertThrows(InvalidPitException.class, () -> gameService.sowStonesFromPit(userFinal1, board.getKalahOne(), boardFinal));
    }
}