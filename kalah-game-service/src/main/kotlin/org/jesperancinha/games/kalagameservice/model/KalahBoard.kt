package org.jesperancinha.games.kalagameservice.model

import org.hibernate.Hibernate
import org.jesperancinha.games.kalagameservice.dto.BoardDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table
data class KalahBoard(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    internal var id: Long? = null,

    @OneToMany
    internal var kalahWashers: List<KalahWasher>? = null,

    @OneToOne
    internal var kalahWasherOne: KalahWasher? = null,

    @OneToOne
    internal var kalahOne: KalahTable? = null,

    @OneToOne
    internal var playerOne: Player? = null,

    @OneToOne
    internal var kalahWasherTwo: KalahWasher? = null,

    @OneToOne
    internal var kalahTwo: KalahTable? = null,

    @OneToOne
    internal var playerTwo: Player? = null,

    @OneToOne
    internal var currentPlayer: Player? = null,

    @OneToOne
    internal var winner: Player? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as KalahBoard

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , pitOne = $kalahWasherOne , kalahOne = $kalahOne , playerOne = $playerOne , pitTwo = $kalahWasherTwo , kalahTwo = $kalahTwo , playerTwo = $playerTwo , currentPlayer = $currentPlayer , winner = $winner )"
    }
}

internal val KalahBoard.toDto: BoardDto?
    get() = this.kalahWashers?.map { obj: KalahWasher -> obj.toDto }?.let { it1 ->
        BoardDto(
            id = this.id,
            currentPlayerDto = this.currentPlayer?.toDto,
            playerDtoOne = this.playerOne?.toDto,
            playerDtoTwo = this.playerTwo?.toDto,
            washerDtoOne = this.kalahWasherOne?.toDto,
            pitDtoTwo = this.kalahWasherTwo?.toDto,
            pitDtos = it1.toList(),
            winnerDto = this.winner?.toDto,
            kalahOne = null,
            kalahTwo = null
        )
    }



