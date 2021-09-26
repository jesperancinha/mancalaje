package org.jesperancinha.games.kalagameservice.dto.converters

import org.jesperancinha.games.kalagameservice.dto.PitDto
import org.jesperancinha.games.kalagameservice.model.Pit

object PitConverter {
    fun toDto(pit: Pit?): PitDto? {
        return pit?.let {
            PitDto(
                id = pit.id,
                pitType = pit.pitType,
                stones = pit.stones,
                playerDto = PlayerConverter.toDto(pit.player)
            )
        }
    }
}