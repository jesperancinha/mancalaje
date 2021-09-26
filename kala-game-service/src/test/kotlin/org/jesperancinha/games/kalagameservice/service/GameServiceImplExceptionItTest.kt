package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.exception.GameOverException
import org.jesperancinha.games.kalagameservice.exception.InvalidPitException
import org.jesperancinha.games.kalagameservice.exception.NotOwnedPitException
import org.jesperancinha.games.kalagameservice.exception.WrongTurnException
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@Transactional
internal open class GameServiceImplExceptionItTest {
    @Autowired
    private val gameService: GameServiceImpl? = null

    @Autowired
    private val kalaPlayerRepository: KalaPlayerRepository? = null

    @Test
    fun testSowStonesFromPit_whenTryingToSowInAWrongTurn_thenFail() {
        val user1 = kalaPlayerRepository!!.save(Player(
            username= "user1"))
        val user2 = kalaPlayerRepository.save(Player(
            username= "user2"
        ))
        user1.opponent= user2
        user2.opponent= user1
        val userFinal1 = kalaPlayerRepository.save(user1)
        val userFinal2 = kalaPlayerRepository.save(user2)
        val board = gameService!!.createNewBoard(userFinal1)
        val boardFinal = gameService.joinPlayer(userFinal2, board)
        Assertions.assertThrows(WrongTurnException::class.java) {
            board?.pitTwo?.let {
                gameService.sowStonesFromPit(userFinal2,
                    it,
                    boardFinal)
            }
        }
    }

    @Test
    fun testSowStonesFromPit_whenTryingToSowInAWrongPit_thenFail() {
        val user1 = kalaPlayerRepository!!.save(Player(
            username= "user1"))
        val user2 = kalaPlayerRepository.save(Player(
            username= "user2"))
        user1.opponent= user2
        user2.opponent= user1
        val userFinal1 = kalaPlayerRepository.save(user1)
        val userFinal2 = kalaPlayerRepository.save(user2)
        val board = gameService!!.createNewBoard(userFinal1)
        val boardFinal = gameService.joinPlayer(userFinal2, board)
        Assertions.assertThrows(NotOwnedPitException::class.java) {
            board?.pitTwo?.let {
                gameService.sowStonesFromPit(userFinal1,
                    it,
                    boardFinal)
            }
        }
    }

    @Test
    fun testSowStonesFromPit_whenTryingToSowFromAKalah_thenFail() {
        val user1 = kalaPlayerRepository!!.save(Player(
            username= "user1")
        )
        val user2 = kalaPlayerRepository.save(Player(
            username= "user2"))
        user1.opponent= user2
        user2.opponent= user1
        val userFinal1 = kalaPlayerRepository.save(user1)
        val userFinal2 = kalaPlayerRepository.save(user2)
        val board = gameService!!.createNewBoard(userFinal1)
        val boardFinal = gameService.joinPlayer(userFinal2, board)
        Assertions.assertThrows(InvalidPitException::class.java) {
            board?.kalahOne?.let {
                gameService.sowStonesFromPit(userFinal1,
                    it,
                    boardFinal)
            }
        }
    }

    @Test
    fun testSowStonesFromPit_whenGameOver_thenFail() {
        val user1 = kalaPlayerRepository!!.save(Player(
            username= "user1"))
        val user2 = kalaPlayerRepository.save(Player(
            username= "user2"))
        user1.opponent= user2
        user2.opponent= user1
        val userFinal1 = kalaPlayerRepository.save(user1)
        val userFinal2 = kalaPlayerRepository.save(user2)
        val board = gameService!!.createNewBoard(userFinal1)
        val boardFinal = gameService.joinPlayer(userFinal2, board)
        boardFinal?.winner= user1
        Assertions.assertThrows(GameOverException::class.java) {
            board?.pitOne?.let {
                gameService.sowStonesFromPit(userFinal1,
                    it,
                    boardFinal)
            }
        }
    }
}