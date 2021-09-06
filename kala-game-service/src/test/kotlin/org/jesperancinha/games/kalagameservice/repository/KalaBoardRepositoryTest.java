package org.jesperancinha.games.kalagameservice.repository;

import org.jesperancinha.games.kalagameservice.model.Board;
import org.jesperancinha.games.kalagameservice.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class KalaBoardRepositoryTest {

    @Autowired
    private KalaBoardRepository kalaBoardRepository;

    @Autowired
    private KalaPlayerRepository kalaPlayerRepository;

    private Player player1;

    private Player player3;

    @BeforeEach
    @Transactional
    public void setUp() {
        player1 = kalaPlayerRepository.save(Player.builder().username("player1").build());
        final Player player2 = kalaPlayerRepository.save(Player.builder().username("player2").build());
        player3 = kalaPlayerRepository.save(Player.builder().username("player3").build());
        final Board boardPlayerOne = new Board();
        final Board boardPlayerTwo = new Board();
        final Board boardPlayerThree = new Board();
        boardPlayerOne.setPlayerOne(player1);
        boardPlayerTwo.setPlayerOne(player2);
        boardPlayerThree.setPlayerOne(player3);
        boardPlayerThree.setPlayerTwo(player2);
        kalaBoardRepository.save(boardPlayerOne);
        kalaBoardRepository.save(boardPlayerTwo);
        kalaBoardRepository.save(boardPlayerThree);
    }

    @Test
    void testFindBoardsByPlayerOneEquals_whenPlayerOne_thenShowPlayerOneBoards() {
        final List<Board> boardsByPlayerOneEquals = kalaBoardRepository.findBoardsByPlayerOneEquals(player1);

        assertThat(boardsByPlayerOneEquals).hasSize(1);
        assertThat(boardsByPlayerOneEquals.get(0).getPlayerOne().getUsername()).isEqualTo("player1");
    }

    @Test
    void testFindBoardsByPlayerOneEquals_whenPlayerThree_thenShowPlayerThreeBoards() {
        final List<Board> boardsByPlayerOneEquals = kalaBoardRepository.findBoardsByPlayerOneEquals(player3);

        assertThat(boardsByPlayerOneEquals).hasSize(1);
        assertThat(boardsByPlayerOneEquals.get(0).getPlayerOne().getUsername()).isEqualTo("player3");
    }

    @Test
    void findBoardsByPlayerTwoIsNull_whenCalled_thenShowAllBoards() {
        final List<Board> boardsByPlayerTwoIsNull = kalaBoardRepository.findBoardsByPlayerTwoIsNull();
        assertThat(boardsByPlayerTwoIsNull).hasSize(2);
        assertThat(boardsByPlayerTwoIsNull.get(0).getPlayerOne().getUsername()).isEqualTo("player1");
        assertThat(boardsByPlayerTwoIsNull.get(1).getPlayerOne().getUsername()).isEqualTo("player2");
    }
}