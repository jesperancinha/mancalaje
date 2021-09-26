package org.jesperancinha.games.kalagameservice.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

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
)