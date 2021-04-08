package org.jesperancinha.games.kalagameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long id;

    private String username;

    private List<BoardDto> boardDtos;

    private PlayerDto opponent;
}
