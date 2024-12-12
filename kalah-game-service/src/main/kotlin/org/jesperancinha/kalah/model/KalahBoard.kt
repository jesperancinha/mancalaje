package org.jesperancinha.kalah.model

import jakarta.persistence.*
import jakarta.persistence.FetchType.LAZY
import org.hibernate.Hibernate
import org.jesperancinha.kalah.dto.BoardDto
import org.springframework.data.annotation.Version
import java.time.Instant
import java.util.UUID

@Entity
@Table
data class KalahBoard(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    internal var id: UUID? = null,

    @OneToMany(fetch = LAZY)
    internal var kalahWashers: List<KalahWasher>? = null,

    @ManyToOne(fetch = LAZY)
    internal var kalahWasherOne: KalahWasher? = null,

    @ManyToOne(fetch = LAZY)
    internal var kalahWasherTwo: KalahWasher? = null,

    @OneToOne(fetch = LAZY)
    internal var kalahTableOne: KalahTable? = null,

    @OneToOne(fetch = LAZY)
    internal var kalahTableTwo: KalahTable? = null,

    @OneToOne(fetch = LAZY)
    internal var playerOne: Player? = null,

    @OneToOne(fetch = LAZY)
    internal var playerTwo: Player? = null,

    @OneToOne(fetch = LAZY)
    internal var currentPlayer: Player? = null,

    @OneToOne(fetch = LAZY)
    internal var winner: Player? = null,

    @Version
    internal var version: Int? = null,

    @ManyToOne(fetch = LAZY)
    internal val owner: Player,

    @Column
    internal val createdAt: Long = Instant.now().toEpochMilli()

) {

    fun setPlayerTwo(playerTwo: Player?){
        this.playerTwo = playerTwo
        this.playerOne?.opponent = playerTwo
        this.playerTwo?.opponent = playerOne
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



