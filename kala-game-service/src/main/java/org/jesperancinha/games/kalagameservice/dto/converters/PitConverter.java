package org.jesperancinha.games.kalagameservice.dto.converters;

import org.jesperancinha.games.kalagameservice.dto.PitDto;
import org.jesperancinha.games.kalagameservice.model.Pit;

import java.util.Objects;

public class PitConverter {
    public static PitDto toDto(Pit pit) {
        if (Objects.isNull(pit)) {
            return null;
        }
        return PitDto
                .builder()
                .id(pit.getId())
                .pitType(pit.getPitType())
                .stones(pit.getStones())
                .playerDto(PlayerConverter.toDto(pit.getPlayer()))
                .build();
    }
}
