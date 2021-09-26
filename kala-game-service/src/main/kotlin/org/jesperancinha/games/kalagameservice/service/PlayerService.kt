package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.model.Player

interface PlayerService {
    fun createPlayer(username: String?): Player?
    fun createOrFindPlayerByName(username: String): Player?
    fun leaveCurrentGame(name: String?)
}