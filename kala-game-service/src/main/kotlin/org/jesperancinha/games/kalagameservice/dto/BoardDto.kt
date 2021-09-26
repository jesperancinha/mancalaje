package org.jesperancinha.games.kalagameservice.dto

data class BoardDto(
    val id: Long?,
    val pitDtos: List<PitDto?>,
    val pitDtoOne: PitDto?,
    val kalahOne: PitDto?,
    val playerDtoOne: PlayerDto?,
    val pitDtoTwo: PitDto?,
    val kalahTwo: PitDto?,
    val playerDtoTwo: PlayerDto?,
    val currentPlayerDto: PlayerDto?,
    val winnerDto: PlayerDto?,
)