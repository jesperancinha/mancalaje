package org.jesperancinha.kalah.model

import org.springframework.data.annotation.Version
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table
data class KalahCup(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "full_cup")
    var full: Boolean,

    @ManyToOne
    var washer: KalahWasher? = null,

    @ManyToOne
    var table: KalahTable? = null,

    @Version
    internal var version: Int? = null
)
