package org.jesperancinha.games.kalagameservice.model

import org.hibernate.Hibernate
import org.jesperancinha.games.kalagameservice.dto.WasherDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table
data class KalahWasher(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @OneToOne
    var nextKalahWasher: KalahWasher? = null,

    @OneToOne
    var oppositeKalahWasher: KalahWasher? = null,

    @OneToOne
    var player: Player? = null,
) : KalahPlace() {
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
