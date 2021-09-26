package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Player

interface BoardService {
    @Throws(Throwable::class)
    fun findBoardById(id: Long): Board?
    fun findBoardsByPlayer(player: Player): MutableList<BoardDto>?
    fun findAvailableBoards(): List<BoardDto>?
}