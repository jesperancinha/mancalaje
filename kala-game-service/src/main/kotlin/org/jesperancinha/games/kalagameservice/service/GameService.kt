package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.exception.*
import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Pit
import org.jesperancinha.games.kalagameservice.model.PitType
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPitRepository
import org.jesperancinha.games.kalagameservice.repository.KalaPlayerRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class GameService(
    private val boardRepository: KalaBoardRepository,
    private val pitRepository: KalaPitRepository,
    private val playerRepository: KalaPlayerRepository,
) {
    fun createNewBoard(player: Player): Board {
        val board = Board(playerOne = player, pitOne = Pit())
        val pits = ArrayList<Pit>()
        board.pits = pits
        var lastPit = Pit(
            player = player,
            pitType = PitType.SMALL,
            stones = 6
        )
        lastPit = pitRepository.save(lastPit)
        board.pitOne = lastPit
        val firstPit = lastPit
        for (i in 0..4) {
            val pit = Pit(
                player = player,
                pitType = PitType.SMALL,
                stones = 6
            )
            lastPit.nextPit = pit
            pits.add(lastPit)
            lastPit = pit
            lastPit = pitRepository.save(lastPit)
        }
        pits.add(lastPit)
        val kalahPit = Pit(
            player = player,
            pitType = PitType.LARGE,
            stones = 0
        )
        lastPit.nextPit = kalahPit
        lastPit = kalahPit
        lastPit = pitRepository.save(lastPit)
        for (i in 0..5) {
            val pit = Pit(
                pitType = PitType.SMALL,
                stones = 6
            )
            val oppositePit = pits[i]
            pit.oppositePit = oppositePit
            oppositePit.oppositePit = pit
            if (i == 0) {
                board.pitTwo = pit
            }
            lastPit.nextPit = pit
            pits.add(lastPit)
            lastPit = pit
            lastPit = pitRepository.save(lastPit)
        }
        pits.add(lastPit)
        val kalahPit2 = Pit(
            pitType = PitType.LARGE,
            stones = 0
        )
        lastPit.nextPit = kalahPit2
        lastPit = kalahPit2
        lastPit = pitRepository.save(lastPit)
        pits.add(lastPit)
        lastPit.nextPit = firstPit
        board.pits = pits
        pits.forEach(Consumer { s: Pit -> pitRepository.save(s) })
        board.currentPlayer = player
        board.playerOne = player
        if (Objects.isNull(player.boards)) {
            player.boards = ArrayList()
        }
        board.kalahOne = kalahPit
        board.kalahTwo = kalahPit2
        val registeredBoard = boardRepository.save(board)
        player.boards?.add(board)
        playerRepository.save(player)
        return registeredBoard
    }

    fun sowStonesFromPit(player: Player, pit: Pit?, board: Board): Board {
        if (Objects.isNull(board.playerTwo)) {
            throw PlayerNotJoinedYetException()
        }
        if (Objects.nonNull(board.winner)) {
            throw GameOverException()
        }
        if (pit?.pitType == PitType.LARGE) {
            throw InvalidPitException()
        }
        if (pit?.player?.username != player.username) {
            throw NotOwnedPitException()
        }
        if (player.username != board.currentPlayer?.username) {
            throw WrongTurnException()
        }
        var stones = pit?.stones ?: 0
        pit?.stones = 0
        var currentPit = pit?.nextPit
        while (stones >= 0) {
            currentPit?.stones = (currentPit?.stones ?: 0) + 1
            stones--
            if (stones == 0) {
                if (currentPit?.player?.username == player.username) {
                    if (currentPit?.pitType == PitType.LARGE) {
                        board?.pits?.forEach(Consumer { s: Pit -> pitRepository.save(s) })
                        return board
                    }
                    val total = (currentPit?.stones ?: 0) + (currentPit?.oppositePit?.stones ?: 0)
                    currentPit?.stones = 0
                    currentPit?.oppositePit?.stones = 0
                    if (player.username == board?.playerOne?.username) {
                        board?.kalahOne?.stones = (board?.kalahOne?.stones ?: 0) + total
                    } else if (player.username == board?.playerTwo?.username) {
                        board?.kalahTwo?.stones = (board?.kalahOne?.stones ?: 0) + total
                    }
                    checkWinner(board)
                    board?.pits?.forEach(Consumer { s: Pit -> pitRepository.save(s) })
                    return board
                }
                break
            }
            currentPit = currentPit?.nextPit
            if (currentPit?.pitType == PitType.LARGE && currentPit.player?.username != player.username) {
                currentPit = currentPit.nextPit
            }
        }
        board.currentPlayer = player.opponent
        board.pits?.forEach(Consumer { s: Pit -> pitRepository.save(s) })
        checkWinner(board)
        return boardRepository.save(board)
    }

    private fun checkWinner(board: Board?) {
        if (board?.pitOne?.let { isWinner(it) } == true) {
            board.winner = board.playerOne
        } else if (board?.pitTwo?.let { isWinner(it) } == true) {
            board.winner = board.playerTwo
        }
    }

    private fun isWinner(pit: Pit?): Boolean {
        var itPit = pit
        var winnerone = true
        while (itPit?.pitType != PitType.LARGE) {
            if ((itPit?.stones ?: 0) > 0) {
                winnerone = false
                break
            }
            itPit = itPit?.nextPit
        }
        return winnerone
    }

    fun joinPlayer(playerTwo: Player, board: Board): Board {
        var pitTwo = board.pitTwo
        while (Objects.isNull(pitTwo?.player)) {
            pitTwo?.player = playerTwo
            pitTwo = pitTwo?.nextPit
        }
        board.pits?.forEach(Consumer { s: Pit -> pitRepository.save(s) })
        board.playerTwo = playerTwo
        playerTwo.opponent = board.playerOne
        playerTwo.currentBoard = board
        val playerOne = board.playerOne
        playerOne.opponent = playerTwo
        playerOne.currentBoard = board
        playerRepository.save(playerOne)
        playerRepository.save(playerTwo)
        return boardRepository.save(board)
    }
}