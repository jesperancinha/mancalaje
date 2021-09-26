package org.jesperancinha.games.kalagameservice.dto

import org.jesperancinha.games.kalagameservice.model.PitType

data class PitDto(
    val id: Long?,
    val pitType: PitType?,
    val stones: Int?,
    val playerDto: PlayerDto?,
)