package org.jesperancinha.games.kalagameservice.dto.converters;

import org.jesperancinha.games.kalagameservice.dto.BoardDto;
import org.jesperancinha.games.kalagameservice.model.Board;

import java.util.stream.Collectors;

public class BoardConverter {
    public static BoardDto toDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .currentPlayerDto(PlayerConverter.toDto(board.getCurrentPlayer()))
                .pitDtos((board.getPits().stream().map(PitConverter::toDto).collect(Collectors.toList())))
                .winnerDto(PlayerConverter.toDto(board.getWinner()))
                .build();
    }
}
