package org.jesperancinha.games.kalagameservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BoardDto(
    @JsonProperty("id")
    val id: Long?,
    @JsonProperty("pitDtos")
    val pitDtos: List<PitDto?>,
    @JsonProperty("pitDtoOne")
    val pitDtoOne: PitDto?,
    @JsonProperty("kalahOne")
    val kalahOne: PitDto?,
    @JsonProperty("playerDtoOne")
    val playerDtoOne: PlayerDto?,
    @JsonProperty("pitDtoTwo")
    val pitDtoTwo: PitDto?,
    @JsonProperty("kalahTwo")
    val kalahTwo: PitDto?,
    @JsonProperty("playerDtoTwo")
    val playerDtoTwo: PlayerDto?,
    @JsonProperty("currentPlayerDto")
    val currentPlayerDto: PlayerDto?,
    @JsonProperty("winnerDto")
    val winnerDto: PlayerDto?,
)