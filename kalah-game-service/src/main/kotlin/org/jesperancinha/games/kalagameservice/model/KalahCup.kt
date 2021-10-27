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
class KalahCup(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column
    var full: Boolean,

    @OneToOne
    var washer: KalahWasher? = null,

    @OneToOne
    var table: KalahTable? = null
)
