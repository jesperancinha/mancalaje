package org.jesperancinha.kalah.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class WasherDto(
    @JsonProperty("id")
    val id: UUID?,
    @JsonProperty("playerDto")
    val playerDto: PlayerDto?,
)