package org.jesperancinha.games.kalagameservice.dto.converters;

import org.jesperancinha.games.kalagameservice.dto.PlayerDto;
import org.jesperancinha.games.kalagameservice.model.Player;

import java.util.Objects;

public class PlayerConverter {
    public static PlayerDto toDto(Player currentPlayer) {
        if (Objects.isNull(currentPlayer)) {
            return null;
        }
        return PlayerDto.builder()
                .id(currentPlayer.getId())
                .username(currentPlayer.getUsername())
                .build();
    }
}
