package org.jesperancinha.kalah.model

import org.hibernate.Hibernate
import org.jesperancinha.kalah.dto.PlayerDto
import org.springframework.data.annotation.Version
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table
data class Player(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    internal var id: UUID? = null,

    @Column(unique = true)
    internal val username: String,

    @OneToOne
    internal var kalahBoard: KalahBoard? = null,

    @OneToOne
    internal var opponent: Player? = null,

    @OneToOne
    internal var currentKalahBoard: KalahBoard? = null,

    @Version
    internal var version: Int? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Player

        return id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , username = $username , opponent = $opponent , currentBoard = $currentKalahBoard )"
    }
}

val Player.toDto: PlayerDto
    get() = PlayerDto(
        id = this.id,
        username = this.username
    )
