package org.jesperancinha.games.kalagameservice.service

import org.jesperancinha.games.kalagameservice.model.Board
import org.jesperancinha.games.kalagameservice.model.Pit
import org.jesperancinha.games.kalagameservice.model.Player

interface GameService {
    fun createNewBoard(player: Player): Board?
    fun sowStonesFromPit(player: Player, pit: Pit, board: Board?): Board?
    fun joinPlayer(player: Player, board: Board?): Board?
}