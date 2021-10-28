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
import org.jesperancinha.games.kalagameservice.repository.KalahPlayerRepository
import org.jesperancinha.games.kalagameservice.repository.KalahTableRepository
import org.jesperancinha.games.kalagameservice.repository.KalahWasherRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class GameService(
    private val tableRepository: KalahTableRepository,
    private val boardRepository: KalahBoardRepository,
    private val kalahWasherRepository: KalahWasherRepository,
    private val playerRepository: KalahPlayerRepository,
) {
    fun createNewBoard(player: Player): KalahBoard {
        val kalahBoard = KalahBoard(owner = player)
        val kalahWashers = mutableListOf<KalahWasher>()
        kalahBoard.kalahWashers = kalahWashers
        val kalahTable = KalahTable()
        val lastWasher = generateHalfBoard(kalahBoard, kalahWashers, kalahTable)
        val kalahTable2 = KalahTable()
        lastWasher.nextKalahTable = kalahTable2
        val lastWasher2 = generateHalfBoard(kalahBoard, kalahWashers, kalahTable2)
        lastWasher2.nextKalahTable = kalahTable
        kalahWashers.forEach { s: KalahWasher -> kalahWasherRepository.save(s) }
        kalahBoard.kalahOne = kalahTable
        kalahBoard.kalahTwo = kalahTable2
        val registeredBoard = boardRepository.save(kalahBoard)
        player.kalahBoard = registeredBoard
        playerRepository.save(player)
        return registeredBoard
    }

    private fun generateHalfBoard(
        kalahBoard: KalahBoard,
        kalahWashers: MutableList<KalahWasher>,
        kalahTable: KalahTable
    ): KalahWasher {
        tableRepository.save(kalahTable)
        var lastKalahWasher = KalahWasher()
        kalahTable.nextKalahWasher = lastKalahWasher
        lastKalahWasher = kalahWasherRepository.save(lastKalahWasher)
        kalahBoard.kalahWasherOne = lastKalahWasher
        kalahWashers.add(lastKalahWasher)
        for (i in 0..4) {
            val kalahWasher = KalahWasher()
            lastKalahWasher.nextKalahWasher = kalahWasher
            kalahWashers.add(lastKalahWasher)
            lastKalahWasher = kalahWasher
            lastKalahWasher = kalahWasherRepository.save(lastKalahWasher)
        }

        return lastKalahWasher
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
        kalahBoard.kalahWashers?.forEach(Consumer { s: KalahWasher -> kalahWasherRepository.save(s) })
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
        kalahBoard.kalahWashers?.forEach(Consumer { s: KalahWasher -> kalahWasherRepository.save(s) })
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