package org.jesperancinha.games.kalagameservice.repository;

import org.jesperancinha.games.kalagameservice.model.Player;
import org.jesperancinha.games.kalagameservice.service.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class KalaPlayerRepositoryItTest {
    @Autowired
    private KalaPlayerRepository kalaPlayerRepository;

    @Autowired
    private GameServiceImpl gameService;

    @Test
    void testFindBoardByPlayerOneUsername_whenSearchUserName_thenFindOneBoard() {
        var user1 = Player.builder().username("user1").build();
        user1 = kalaPlayerRepository.save(user1);
        gameService.createNewBoard(user1);

        final Optional<Player> playerOptional = kalaPlayerRepository.findById(user1.getId());

        assertThat(playerOptional.isPresent()).isTrue();
        assertThat(playerOptional.get().getBoards()).hasSize(1);
    }
}