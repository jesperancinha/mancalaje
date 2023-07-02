package org.jesperancinha.kalah.service

import org.jesperancinha.kalah.exception.GameOverException
import org.jesperancinha.kalah.exception.NotOwnedPitException
import org.jesperancinha.kalah.exception.PlayerNotJoinedYetException
import org.jesperancinha.kalah.exception.WrongTurnException
import org.jesperancinha.kalah.model.KalahBoard
import org.jesperancinha.kalah.model.KalahCup
import org.jesperancinha.kalah.model.KalahTable
import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.model.Player
import org.jesperancinha.kalah.repository.KalahBoardRepository
import org.jesperancinha.kalah.repository.KalahPlayerRepository
import org.jesperancinha.kalah.repository.KalahTableRepository
import org.springframework.data.repository.findByIdOrNull
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
        val lastWasher2 = generateHalfBoard(kalahWashers, kalahTable2) { kalahBoard.kalahWasherTwo = it }
        lastWasher2.nextKalahTable = kalahTable
        kalahWashers.forEach { kalahWasher: KalahWasher -> kalahWasherService.update(kalahWasher) }
        kalahBoard.kalahTableOne = tableRepository.findByIdOrNull(kalahTable.id)
        kalahBoard.kalahTableTwo = tableRepository.findByIdOrNull(kalahTable2.id)
        return boardRepository.save(kalahBoard)
    }

    private fun generateHalfBoard(
        kalahWashers: MutableList<KalahWasher>,
        kalahTable: KalahTable,
        pivotTo: (KalahWasher) -> Unit,
    ): KalahWasher {
        tableRepository.save(kalahTable)
        var lastKalahWasher = createKalahWasher()
        kalahTable.nextKalahWasher = lastKalahWasher
        pivotTo(lastKalahWasher)
        kalahWashers.add(lastKalahWasher)
        lastKalahWasher = kalahWasherService.create(lastKalahWasher)
        repeat(5) {
            val kalahWasher = createKalahWasher()
            lastKalahWasher.nextKalahWasher = kalahWasher
            kalahWashers.add(lastKalahWasher)
            lastKalahWasher = kalahWasher
            lastKalahWasher = kalahWasherService.update(lastKalahWasher)
        }
        return lastKalahWasher
    }

    private fun createKalahWasher() = kalahWasherService.create(KalahWasher())

    fun rolloutCupsFromPayersWasherOnBoard(
        player: Player,
        kalahWasher: KalahWasher?,
        kalahBoard: KalahBoard
    ): KalahBoard {
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

        val cups = kalahWasher.cups
        kalahWasher.cups = mutableListOf()
        rolloutCupsFromPayersWasherOnBoard(player, cups.size, cups, kalahWasher, { it.cups }, { it?.cups })

        kalahBoard.currentPlayer = player.opponent
        kalahBoard.kalahWashers?.forEach(Consumer { kw: KalahWasher -> kalahWasherService.update(kw) })
        checkWinner(kalahBoard)
        return kalahBoard
    }

    private fun rolloutCupsFromPayersWasherOnBoard(
        player: Player,
        size: Int,
        cups: MutableList<KalahCup>,
        kalahWasher: KalahWasher,
        washerCups: (KalahWasher) -> MutableList<KalahCup>?,
        tableCups: (KalahTable?) -> MutableList<KalahCup>?,
    ) {
        if (cups.isNotEmpty()) {
            kalahWasher.nextKalahWasher?.let { nextKalahWasher ->
                washerCups(nextKalahWasher)?.add(cups[0])
                if (cups.size > 1) {
                    rolloutCupsFromPayersWasherOnBoard(
                        player,
                        size,
                        cups.subList(1, cups.size),
                        nextKalahWasher,
                        washerCups,
                        tableCups
                    )
                }
            }
            kalahWasher.nextKalahTable?.let { nextKalahTable ->
                tableCups(nextKalahTable)?.add(cups[0])
                if (cups.size > 1) {
                    sowCupsFromTable(
                        player,
                        size,
                        cups.subList(1, cups.size),
                        nextKalahTable,
                        washerCups,
                        tableCups
                    )
                } else {
                    if (nextKalahTable.player != player) {
                        repeat(size) {
                            nextKalahTable.cups.remove(nextKalahTable.cups.random())
                        }
                    }
                }

            }
        }
    }

    private fun sowCupsFromTable(
        player: Player,
        size: Int,
        cups: MutableList<KalahCup>,
        kalahTable: KalahTable?,
        washerCups: (KalahWasher) -> MutableList<KalahCup>?,
        tableCups: (KalahTable?) -> MutableList<KalahCup>?,
    ) {
        if (cups.isNotEmpty()) {
            kalahTable?.nextKalahWasher?.let { nextKalahWasher ->
                kalahTable.nextKalahWasher?.cups?.add(cups[0])
                if (cups.size > 1) {
                    return rolloutCupsFromPayersWasherOnBoard(
                        player,
                        size,
                        cups.subList(1, cups.size),
                        nextKalahWasher,
                        washerCups,
                        tableCups
                    )
                } else {
                    if (kalahTable.player != player) {
                        kalahTable.cups.clear()
                    }
                }
            }
        }
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

    fun joinPlayer(player: Player, kalahBoard: KalahBoard): KalahBoard {
        return kalahBoard.playerOne?.let {
            joinPlayer(player, kalahBoard, { kalahBoard.setPlayerTwo(player) }, { kalahBoard.kalahTableTwo })
        } ?: joinPlayer(player, kalahBoard, { kalahBoard.playerOne = player }, { kalahBoard.kalahTableOne })
    }

    private fun joinPlayer(
        player: Player,
        kalahBoard: KalahBoard,
        setPlayer: () -> Unit,
        getTable: () -> KalahTable?
    ): KalahBoard {
        val table = getTable()
        table?.player = player
        table?.nextKalahWasher?.also { firstKalahWasher ->
            var currKalahWasher: KalahWasher? = firstKalahWasher
            while (currKalahWasher != null) {
                currKalahWasher.player = player
                currKalahWasher = currKalahWasher.nextKalahWasher
            }
        }

        kalahBoard.kalahWashers?.forEach { kw: KalahWasher -> kalahWasherService.update(kw) }
        setPlayer()
        player.currentKalahBoard = kalahBoard
        playerRepository.save(player)
        return boardRepository.save(kalahBoard)
    }
}
