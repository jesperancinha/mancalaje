package org.jesperancinha.games.kalagameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jesperancinha.games.kalagameservice.model.Player;

import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long id;

    private List<PitDto> pitDtos;

    private PitDto pitDtoOne;

    private PitDto kalahOne;

    private PlayerDto playerDtoOne;

    private PitDto pitDtoTwo;

    private PitDto kalahTwo;

    private PlayerDto playerDtoTwo;

    private PlayerDto currentPlayerDto;

    private PlayerDto winnerDto;

}
