package org.jesperancinha.games.kalagameservice.model

import org.springframework.data.annotation.Version
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table
data class KalahCup(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column
    var full: Boolean,

    @ManyToOne
    var washer: KalahWasher? = null,

    @OneToOne
    var table: KalahTable? = null,

    @Version
    internal var version: Int
)
