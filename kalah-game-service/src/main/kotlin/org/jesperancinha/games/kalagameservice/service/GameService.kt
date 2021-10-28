package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.exception.GameOverException
import org.jesperancinha.games.kalagameservice.exception.NotOwnedPitException
import org.jesperancinha.games.kalagameservice.exception.PlayerNotJoinedYetException
import org.jesperancinha.games.kalagameservice.exception.WrongTurnException
import org.jesperancinha.games.kalagameservice.model.KalahBoard
import org.jesperancinha.games.kalagameservice.model.KalahTable
import org.jesperancinha.games.kalagameservice.model.KalahWasher
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalahBoardRepository
import org.jesperancinha.games.kalagameservice.repository.KalahWasherRepository
import org.jesperancinha.games.kalagameservice.repository.KalahPlayerRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class GameService(
    private val boardRepository: KalahBoardRepository,
    private val pitRepository: KalahWasherRepository,
    private val playerRepository: KalahPlayerRepository,
) {
    fun createNewBoard(player: Player): KalahBoard {
        val kalahBoard = KalahBoard(playerOne = player)
        val kalahWashers = ArrayList<KalahWasher>()
        kalahBoard.kalahWashers = kalahWashers
        var lastKalahWasher = KalahWasher(
            player = player
        )
        lastKalahWasher = pitRepository.save(lastKalahWasher)
        kalahBoard.kalahWasherOne = lastKalahWasher
        val firstPit = lastKalahWasher
        for (i in 0..4) {
            val kalahWasher = KalahWasher(
                player = player
            )
            lastKalahWasher.nextKalahWasher = kalahWasher
            kalahWashers.add(lastKalahWasher)
            lastKalahWasher = kalahWasher
            lastKalahWasher = pitRepository.save(lastKalahWasher)
        }
        kalahWashers.add(lastKalahWasher)
        val kalahTable = KalahTable(
            player = player
        )
        lastKalahWasher.nextKalahTable = kalahTable
        lastKalahWasher = pitRepository.save(lastKalahWasher)
        for (i in 0..5) {
            val kalahWasher = KalahWasher()
            val oppositePit = kalahWashers[i]
            kalahWasher.oppositeKalahWasher = oppositePit
            oppositePit.oppositeKalahWasher = kalahWasher
            if (i == 0) {
                kalahBoard.kalahWasherTwo = kalahWasher
            }
            lastKalahWasher.nextKalahWasher = kalahWasher
            kalahWashers.add(lastKalahWasher)
            lastKalahWasher = kalahWasher
            lastKalahWasher = pitRepository.save(lastKalahWasher)
        }
        kalahWashers.add(lastKalahWasher)
        val kalahKalahWasher2 = KalahTable()
        lastKalahWasher.nextKalahTable = kalahKalahWasher2
        lastKalahWasher = pitRepository.save(lastKalahWasher)
        kalahWashers.add(lastKalahWasher)
        lastKalahWasher.nextKalahWasher = firstPit
        kalahBoard.kalahWashers = kalahWashers
        kalahWashers.forEach(Consumer { s: KalahWasher -> pitRepository.save(s) })
        kalahBoard.currentPlayer = player
        kalahBoard.playerOne = player
        kalahBoard.kalahOne = kalahTable
        kalahBoard.kalahTwo = kalahKalahWasher2
        val registeredBoard = boardRepository.save(kalahBoard)
        player.kalahBoard= kalahBoard
        playerRepository.save(player)
        return registeredBoard
    }

    fun sowStonesFromPit(player: Player, kalahWasher: KalahTable?, kalahBoard: KalahBoard): KalahBoard {
        if (Objects.isNull(kalahBoard.playerTwo)) {
            throw PlayerNotJoinedYetException()
        }
        if (Objects.nonNull(kalahBoard.winner)) {
            throw GameOverException()
        }
        if (kalahWasher?.player?.username != player.username) {
            throw NotOwnedPitException()
        }
        if (player.username != kalahBoard.currentPlayer?.username) {
            throw WrongTurnException()
        }
//        var stones = kalahWasher?.stones ?: 0
//        kalahWasher?.stones = 0
        var currentPit = kalahWasher?.nextKalahWasher
//        while (stones >= 0) {
//            currentPit?.stones = (currentPit?.stones ?: 0) + 1
//            stones--
//            if (stones == 0) {
//                if (currentPit?.player?.username == player.username) {
//                    if (currentPit?.washerType == WasherType.LARGE) {
//                        kalahBoard?.kalahWashers?.forEach(Consumer { s: KalahWasher -> pitRepository.save(s) })
//                        return kalahBoard
//                    }
//                    val total = (currentPit?.stones ?: 0) + (currentPit?.oppositeKalahWasher?.stones ?: 0)
//                    currentPit?.stones = 0
//                    currentPit?.oppositeKalahWasher?.stones = 0
//                    if (player.username == kalahBoard?.playerOne?.username) {
//                        kalahBoard?.kalahOne?.stones = (kalahBoard?.kalahOne?.stones ?: 0) + total
//                    } else if (player.username == kalahBoard?.playerTwo?.username) {
//                        kalahBoard?.kalahTwo?.stones = (kalahBoard?.kalahOne?.stones ?: 0) + total
//                    }
//                    checkWinner(kalahBoard)
//                    kalahBoard?.kalahWashers?.forEach(Consumer { s: KalahWasher -> pitRepository.save(s) })
//                    return kalahBoard
//                }
//                break
//            }
//            currentPit = currentPit?.nextKalahWasher
//            if (currentPit?.washerType == WasherType.LARGE && currentPit.player?.username != player.username) {
//                currentPit = currentPit.nextKalahWasher
//            }
//        }
        kalahBoard.currentPlayer = player.opponent
        kalahBoard.kalahWashers?.forEach(Consumer { s: KalahWasher -> pitRepository.save(s) })
        checkWinner(kalahBoard)
        return boardRepository.save(kalahBoard)
    }

    private fun checkWinner(kalahBoard: KalahBoard?) {
        if (kalahBoard?.kalahWasherOne?.let { isWinner(it) } == true) {
            kalahBoard.winner = kalahBoard.playerOne
        } else if (kalahBoard?.kalahWasherTwo?.let { isWinner(it) } == true) {
            kalahBoard.winner = kalahBoard.playerTwo
        }
    }

    private fun isWinner(kalahWasher: KalahWasher?): Boolean {
        var itPit = kalahWasher
        var winnerone = true
//        while (itPit?.washerType != WasherType.LARGE) {
//            if ((itPit?.stones ?: 0) > 0) {
//                winnerone = false
//                break
//            }
//            itPit = itPit?.nextKalahWasher
//        }
        return winnerone
    }

    fun joinPlayer(playerTwo: Player, kalahBoard: KalahBoard): KalahBoard {
        var pitTwo = kalahBoard.kalahWasherTwo
        while (Objects.isNull(pitTwo?.player)) {
            pitTwo?.player = playerTwo
            pitTwo = pitTwo?.nextKalahWasher
        }
        kalahBoard.kalahWashers?.forEach(Consumer { s: KalahWasher -> pitRepository.save(s) })
        kalahBoard.playerTwo = playerTwo
        playerTwo.opponent = kalahBoard.playerOne
        playerTwo.currentKalahBoard = kalahBoard
        kalahBoard.playerOne?.apply {
            opponent = playerTwo
            currentKalahBoard = kalahBoard
            playerRepository.save(this)
        }
        playerRepository.save(playerTwo)
        return boardRepository.save(kalahBoard)
    }
}