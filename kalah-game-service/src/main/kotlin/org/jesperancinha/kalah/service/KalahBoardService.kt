package org.jesperancinha.kalah.service

import org.jesperancinha.kalah.dto.BoardDto
import org.jesperancinha.kalah.exception.BoardDoesNotExistException
import org.jesperancinha.kalah.model.KalahBoard
import org.jesperancinha.kalah.model.Player
import org.jesperancinha.kalah.model.toDto
import org.jesperancinha.kalah.repository.KalahBoardRepository
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.stream.Collectors

@Service
class KalahBoardService(private val boardRepository: KalahBoardRepository) {
    fun findBoardById(id: UUID): KalahBoard? {
        return boardRepository.findById(id).orElseThrow { BoardDoesNotExistException() }
    }

    fun findBoardsByPlayer(player: Player): MutableList<BoardDto>? {
        return boardRepository.findBoardsByPlayerOneEquals(player).stream()
            .map { obj: KalahBoard? -> obj?.toDto }.collect(Collectors.toList())
    }

    fun findAvailableBoards(): MutableList<BoardDto>? {
        return boardRepository.findBoardsByPlayerTwoIsNull().stream()
            .map { obj: KalahBoard? -> obj?.toDto }.collect(Collectors.toList())
    }
}