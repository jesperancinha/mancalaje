package org.jesperancinha.games.kalagameservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jesperancinha.games.kalagameservice.model.Pit;
import org.jesperancinha.games.kalagameservice.model.PitType;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PitDto {
    private Long id;

    private PitType pitType;

    private Integer stones;

    private PlayerDto playerDto;
}
