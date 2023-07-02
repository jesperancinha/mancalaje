package org.jesperancinha.kalah.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class BoardDto(
    @JsonProperty("id")
    val id: UUID?,
    @JsonProperty("pitDtos")
    val pitDtos: List<WasherDto?>,
    @JsonProperty("pitDtoOne")
    val washerDtoOne: WasherDto?,
    @JsonProperty("kalahOne")
    val kalahOne: WasherDto?,
    @JsonProperty("playerDtoOne")
    val playerDtoOne: PlayerDto?,
    @JsonProperty("pitDtoTwo")
    val pitDtoTwo: WasherDto?,
    @JsonProperty("kalahTwo")
    val kalahTwo: WasherDto?,
    @JsonProperty("playerDtoTwo")
    val playerDtoTwo: PlayerDto?,
    @JsonProperty("currentPlayerDto")
    val currentPlayerDto: PlayerDto?,
    @JsonProperty("winnerDto")
    val winnerDto: PlayerDto?,
)