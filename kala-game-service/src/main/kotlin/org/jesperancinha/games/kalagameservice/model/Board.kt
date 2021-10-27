package org.jesperancinha.games.kalagameservice.model

import lombok.*
import org.hibernate.Hibernate
import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.dto.converters.PitConverter
import org.jesperancinha.games.kalagameservice.dto.converters.PlayerConverter
import javax.persistence.*

@Entity
@Table
@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    internal var id: Long? = null,

    @OneToMany
    internal var pits: List<Pit>? = null,

    @OneToOne
    internal var pitOne: Pit = Pit(),

    @OneToOne
    internal var kalahOne: Pit? = null,

    @OneToOne
    internal var playerOne: Player,

    @OneToOne
    internal var pitTwo: Pit? = null,

    @OneToOne
    internal var kalahTwo: Pit? = null,

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
        other as Board

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , pitOne = $pitOne , kalahOne = $kalahOne , playerOne = $playerOne , pitTwo = $pitTwo , kalahTwo = $kalahTwo , playerTwo = $playerTwo , currentPlayer = $currentPlayer , winner = $winner )"
    }
}

internal val Board.toDto: BoardDto?
    get() = this.pits?.map { obj: Pit -> PitConverter.toDto(obj) }?.let { it1 ->
        BoardDto(
            id = this.id,
            currentPlayerDto = PlayerConverter.toDto(this.currentPlayer),
            playerDtoOne = PlayerConverter.toDto(this.playerOne),
            playerDtoTwo = PlayerConverter.toDto(this.playerTwo),
            pitDtoOne = PitConverter.toDto(this.pitOne),
            pitDtoTwo = PitConverter.toDto(this.pitTwo),
            pitDtos = it1.toList(),
            winnerDto = PlayerConverter.toDto(this.winner),
            kalahOne = null,
            kalahTwo = null
        )
    }



