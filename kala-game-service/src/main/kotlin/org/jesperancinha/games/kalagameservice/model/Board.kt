package org.jesperancinha.games.kalagameservice.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table
@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
data class Board (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    internal var id: Long? = null,

    @OneToMany
    internal var pits: List<Pit>? = null,

    @OneToOne
    internal var pitOne: Pit? = null,

    @OneToOne
    internal var kalahOne: Pit? = null,

    @OneToOne
    internal var playerOne: Player? = null,

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
)