package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.exception.BoardDoesNotExistException
import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.model.toDto
import org.jesperancinha.games.kalagameservice.repository.KalaBoardRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class BoardService(private val boardRepository: KalaBoardRepository) {
    fun findBoardById(id: Long): Board? {
        return boardRepository.findById(id).orElseThrow { BoardDoesNotExistException() }
    }

    fun findBoardsByPlayer(player: Player): MutableList<BoardDto>? {
        return boardRepository.findBoardsByPlayerOneEquals(player).stream()
            .map { obj: Board? -> obj?.toDto }.collect(Collectors.toList())
    }

    fun findAvailableBoards(): MutableList<BoardDto>? {
        return boardRepository.findBoardsByPlayerTwoIsNull().stream()
            .map { obj: Board? -> obj?.toDto }.collect(Collectors.toList())
    }
}