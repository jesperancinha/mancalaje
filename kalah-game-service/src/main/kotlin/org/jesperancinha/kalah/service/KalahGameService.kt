package org.jesperancinha.kalah.service

import org.jesperancinha.kalah.exception.GameOverException
import org.jesperancinha.kalah.exception.NotOwnedPitException
import org.jesperancinha.kalah.exception.PlayerNotJoinedYetException
import org.jesperancinha.kalah.exception.WrongTurnException
import org.jesperancinha.kalah.model.KalahBoard
import org.jesperancinha.kalah.model.KalahTable
import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.model.Player
import org.jesperancinha.kalah.repository.KalahBoardRepository
import org.jesperancinha.kalah.repository.KalahPlayerRepository
import org.jesperancinha.kalah.repository.KalahTableRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class KalahGameService(
    private val tableRepository: KalahTableRepository,
    private val boardRepository: KalahBoardRepository,
    private val kalahWasherService: KalahWasherService,
    private val playerRepository: KalahPlayerRepository,
) {
    fun createNewBoard(player: Player): KalahBoard {
        val registeredBoard = createKalahBoard(player)
        player.kalahBoard = registeredBoard
        playerRepository.save(player)
        return registeredBoard
    }

    private fun createKalahBoard(player: Player): KalahBoard {
        val kalahBoard = KalahBoard(owner = player)
        val kalahWashers = mutableListOf<KalahWasher>()
        kalahBoard.kalahWashers = kalahWashers
        val kalahTable = KalahTable()
        val lastWasher = generateHalfBoard(kalahWashers, kalahTable) { kalahBoard.kalahWasherOne = it }
        val kalahTable2 = KalahTable()
        lastWasher.nextKalahTable = kalahTable2
        val lastWasher2 = generateHalfBoard(kalahWashers, kalahTable2) { kalahBoard.kalahWasherOne = it }
        lastWasher2.nextKalahTable = kalahTable
        kalahWashers.forEach { kalahWasher: KalahWasher -> kalahWasherService.create(kalahWasher) }
        kalahBoard.kalahOne = kalahTable
        kalahBoard.kalahTwo = kalahTable2
        return boardRepository.save(kalahBoard)
    }

    private fun generateHalfBoard(
        kalahWashers: MutableList<KalahWasher>,
        kalahTable: KalahTable,
        pivotTo: (KalahWasher) -> Unit,
    ): KalahWasher {
        tableRepository.save(kalahTable)
        var lastKalahWasher = KalahWasher()
        kalahTable.nextKalahWasher = lastKalahWasher
        pivotTo(lastKalahWasher)
        kalahWashers.add(lastKalahWasher)
        repeat(5) {
            val kalahWasher = KalahWasher()
            lastKalahWasher.nextKalahWasher = kalahWasher
            kalahWashers.add(lastKalahWasher)
            lastKalahWasher = kalahWasher
            lastKalahWasher = kalahWasherService.create(lastKalahWasher)
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
        kalahBoard.kalahWashers?.forEach(Consumer { kw: KalahWasher -> kalahWasherService.create(kw) })
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
        kalahBoard.kalahWashers?.forEach(Consumer { s: KalahWasher -> kalahWasherService.create(s) })
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