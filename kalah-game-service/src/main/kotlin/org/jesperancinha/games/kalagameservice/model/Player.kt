package org.jesperancinha.games.kalagameservice.model

import org.hibernate.Hibernate
import org.jesperancinha.games.kalagameservice.dto.PlayerDto
import javax.persistence.*

@Entity
@Table
data class Player(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    internal val id: Long? = null,

    @Column(unique = true)
    internal val username: String? = null,

    @OneToMany(fetch = FetchType.EAGER)
    internal var boards: MutableList<Board>? = null,

    @OneToOne
    internal var opponent: Player? = null,

    @OneToOne
    internal var currentBoard: Board? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Player

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , username = $username , opponent = $opponent , currentBoard = $currentBoard )"
    }
}

val Player.toDto: PlayerDto
    get() = PlayerDto(
        id = this.id,
        username = this.username
    )
