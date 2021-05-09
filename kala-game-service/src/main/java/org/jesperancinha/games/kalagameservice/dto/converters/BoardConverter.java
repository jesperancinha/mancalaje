package org.jesperancinha.games.kalagameservice.dto.converters;

import org.jesperancinha.games.kalagameservice.dto.BoardDto;
import org.jesperancinha.games.kalagameservice.model.Board;

import java.util.Objects;
import java.util.stream.Collectors;

public class BoardConverter {
    public static BoardDto toDto(Board board) {
        if (Objects.isNull(board)) {
            return null;
        }
        return BoardDto.builder()
                .id(board.getId())
                .currentPlayerDto(PlayerConverter.toDto(board.getCurrentPlayer()))
                .playerDtoOne(PlayerConverter.toDto(board.getPlayerOne()))
                .playerDtoTwo(PlayerConverter.toDto(board.getPlayerTwo()))
                .pitDtoOne(PitConverter.toDto(board.getPitOne()))
                .pitDtoTwo(PitConverter.toDto(board.getPitTwo()))
                .pitDtos((board.getPits().stream().map(PitConverter::toDto).collect(Collectors.toList())))
                .winnerDto(PlayerConverter.toDto(board.getWinner()))
                .build();
    }
}
