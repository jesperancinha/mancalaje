package org.jesperancinha.kalah.model

import org.springframework.data.annotation.Version
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table
data class KalahTable(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @OneToOne
    var nextKalahWasher: KalahWasher? = null,

    @OneToOne
    var player: Player? = null,

    @OneToMany
    val cups: MutableList<KalahCup> = mutableListOf(),

    @Version
    internal var version: Int? = null
)