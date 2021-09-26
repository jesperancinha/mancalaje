package org.jesperancinha.games.kalagameservice.dto.converters

import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Pit
import kotlin.streams.toList

object BoardConverter {
    @JvmStatic
    fun toDto(board: Board?): BoardDto? {
        return board?.let {
            board.pits?.map { obj: Pit -> PitConverter.toDto(obj) }?.let { it1 ->
                BoardDto(
                    id = board.id,
                    currentPlayerDto = PlayerConverter.toDto(board.currentPlayer),
                    playerDtoOne = PlayerConverter.toDto(board.playerOne),
                    playerDtoTwo = PlayerConverter.toDto(board.playerTwo),
                    pitDtoOne = PitConverter.toDto(board.pitOne),
                    pitDtoTwo = PitConverter.toDto(board.pitTwo),
                    pitDtos = it1.toList(),
                    winnerDto = PlayerConverter.toDto(board.winner),
                    kalahOne = null,
                    kalahTwo = null
                )
            }
        }
    }
}