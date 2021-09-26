package org.jesperancinha.games.kalagameservice.repository

import org.assertj.core.api.Assertions.assertThat
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.service.GameServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@Transactional
internal open class KalaPlayerRepositoryItTest {
    @Autowired
    private val kalaPlayerRepository: KalaPlayerRepository? = null

    @Autowired
    private val gameService: GameServiceImpl? = null

    @Test
    fun testFindBoardByPlayerOneUsername_whenSearchUserName_thenFindOneBoard() {
        var user1 = Player(
            username = "user1"
        )
        user1 = kalaPlayerRepository!!.save<Player>(user1)
        gameService!!.createNewBoard(user1)
        val playerOptional = user1.id?.let { kalaPlayerRepository.findById(user1.id) }
        assertThat(playerOptional?.isPresent).isTrue
        assertThat(playerOptional?.get()?.boards).hasSize(1)
    }
}