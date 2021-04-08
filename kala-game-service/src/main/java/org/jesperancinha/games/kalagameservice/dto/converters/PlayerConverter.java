package org.jesperancinha.games.kalagameservice.dto.converters;

import org.jesperancinha.games.kalagameservice.dto.PlayerDto;
import org.jesperancinha.games.kalagameservice.model.Player;

public class PlayerConverter {
    public static PlayerDto toDto(Player currentPlayer) {
        return PlayerDto.builder()
                .id(currentPlayer.getId())
                .username(currentPlayer.getUsername())
                .build();
    }
}
