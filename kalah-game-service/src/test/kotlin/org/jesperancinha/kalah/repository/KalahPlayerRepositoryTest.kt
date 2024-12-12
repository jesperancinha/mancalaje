package org.jesperancinha.kalah.repository

import org.assertj.core.api.Assertions.assertThat
import org.jesperancinha.kalah.model.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import jakarta.transaction.Transactional
import org.jesperancinha.kalah.containers.AbstractTestContainersIT
import org.jesperancinha.kalah.containers.AbstractTestContainersIT.DockerPostgresDataInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(initializers = [DockerPostgresDataInitializer::class])
class KalahPlayerRepositoryTest @Autowired constructor(
    @Autowired
    private val kalahPlayerRepository: KalahPlayerRepository
) {

    @BeforeEach
    @Transactional
    fun setUp() {
        kalahPlayerRepository.save(Player(
            username = "player1"
        ))
    }

    @Test
    fun testFindPlayerByUsernameEquals_whenLookingPerName_thenFindIt() {
        val player1 = kalahPlayerRepository.findPlayerByUsernameEquals("player1")
        assertThat(player1).isNotNull
        assertThat(player1.username).isEqualTo("player1")
    }
}