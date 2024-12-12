package org.jesperancinha.kalah.model

import org.hibernate.Hibernate
import org.jesperancinha.kalah.dto.WasherDto
import org.springframework.data.annotation.Version
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table
data class KalahWasher(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @OneToOne
    var nextKalahWasher: KalahWasher? = null,

    @OneToOne
    var nextKalahTable: KalahTable? = null,

    @OneToOne
    var oppositeKalahWasher: KalahWasher? = null,

    @OneToOne
    var player: Player? = null,

    @OneToMany
    var cups: MutableList<KalahCup> = mutableListOf(),

    @Version
    internal var version: Int? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as KalahWasher

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , nextWasher = $nextKalahWasher , oppositeWasher = $oppositeKalahWasher , player = $player )"
    }

}

val KalahWasher.toDto: WasherDto
    get() = WasherDto(
        id = this.id,
        playerDto = this.player?.toDto
    )
