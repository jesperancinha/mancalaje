package org.jesperancinha.games.kalagameservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.jesperancinha.games.kalagameservice.model.PitType

data class PitDto(
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("pitType")
    val pitType: PitType?,
    @JsonProperty("stones")
    val stones: Int?,
    @JsonProperty("playerDto")
    val playerDto: PlayerDto?,
)