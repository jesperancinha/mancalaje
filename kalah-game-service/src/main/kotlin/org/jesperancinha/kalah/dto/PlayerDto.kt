package org.jesperancinha.kalah.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class PlayerDto(
    @JsonProperty("id")
    val id: UUID?,
    @JsonProperty("username")
    val username: String?,
)