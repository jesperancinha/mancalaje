package org.jesperancinha.games.kalagameservice.repository

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.service.KalahGameService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@Transactional
internal class KalahPlayerRepositoryItTest(
    @Autowired
    private val kalahPlayerRepository: KalahPlayerRepository
) {
    @Autowired
    private val gameService: KalahGameService? = null

    @Test
    fun testFindBoardByPlayerOneUsername_whenSearchUserName_thenFindOneBoard() {
        var user1 = Player(
            username = "user1"
        )
        user1 = kalahPlayerRepository.save(user1)
        gameService!!.createNewBoard(user1)
        val playerOptional = user1.id?.let { kalahPlayerRepository.findById(it) }
        playerOptional?.apply {
            isPresent.shouldBeTrue()
            get().shouldNotBeNull()
        }
    }
}