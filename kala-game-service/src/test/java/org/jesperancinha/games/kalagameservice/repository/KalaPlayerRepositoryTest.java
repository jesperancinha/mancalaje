package org.jesperancinha.games.kalagameservice.repository;

import org.jesperancinha.games.kalagameservice.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class KalaPlayerRepositoryTest {

    @Autowired
    private KalaPlayerRepository kalaPlayerRepository;

    @BeforeEach
    @Transactional
    public void setUp() {
        kalaPlayerRepository.save(Player.builder().username("player1").build());
    }

    @Test
    void testFindPlayerByUsernameEquals_whenLookingPerName_thenFindIt() {
        final Player player1 = kalaPlayerRepository.findPlayerByUsernameEquals("player1");

        assertThat(player1).isNotNull();
        assertThat(player1.getUsername()).isEqualTo("player1");
    }
}