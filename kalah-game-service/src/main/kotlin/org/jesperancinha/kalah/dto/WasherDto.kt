package org.jesperancinha.kalah.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class WasherDto(
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("playerDto")
    val playerDto: PlayerDto?,
)