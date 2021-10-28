package org.jesperancinha.games.kalagameservice.model

import org.springframework.data.annotation.Version
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

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

    @Version
    internal var version: Int? = null
)