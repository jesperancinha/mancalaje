package org.jesperancinha.games.kalagameservice.dto.converters

import org.jesperancinha.games.kalagameservice.dto.PlayerDto
import org.jesperancinha.games.kalagameservice.model.Player

object PlayerConverter {
    fun toDto(currentPlayer: Player?): PlayerDto? {
        return currentPlayer?.let {
            PlayerDto(
                id = currentPlayer.id,
                username = currentPlayer.username
            )
        }
    }
}