package org.jesperancinha.games.kalagameservice.dto.converters;

import org.jesperancinha.games.kalagameservice.dto.PitDto;
import org.jesperancinha.games.kalagameservice.model.Pit;

public class PitConverter {
    public static PitDto toDto(Pit pit) {
        return PitDto
                .builder()
                .id(pit.getId())
                .pitType(pit.getPitType())
                .stones(pit.getStones())
                .build();
    }
}
