package org.jesperancinha.games.kalagameservice.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table
data class Pit(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column
    var pitType: PitType? = null,

    @Column
    var stones: Int? = null,

    @OneToOne
    var nextPit: Pit? = null,

    @OneToOne
    var oppositePit: Pit? = null,

    @OneToOne
    var player: Player? = null,
)