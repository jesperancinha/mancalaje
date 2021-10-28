package org.jesperancinha.games.kalagameservice.controller

import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.exception.PitDoesNotExistException
import org.jesperancinha.games.kalagameservice.model.KalahTable
import org.jesperancinha.games.kalagameservice.model.KalahWasher
import org.jesperancinha.games.kalagameservice.model.toDto
import org.jesperancinha.games.kalagameservice.service.KalahBoardService
import org.jesperancinha.games.kalagameservice.service.KalahGameService
import org.jesperancinha.games.kalagameservice.service.KalahPlayerService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("api")
class KalaGameController(
    private val gameService: KalahGameService,
    private val boardService: KalahBoardService,
    private val playerService: KalahPlayerService
) {
    @PutMapping("leave")
    @Throws(Throwable::class)
    fun leavegae(principal: Principal) {
        playerService.leaveCurrentGame(principal.name)
    }

    @GetMapping("current")
    @Throws(Throwable::class)
    fun getCurrentBoard(principal: Principal): BoardDto? {
        return playerService.createOrFindPlayerByName(principal.name)?.currentKalahBoard?.toDto
    }

    @PostMapping("create")
    fun createBoard(principal: Principal): BoardDto? {
        val player = playerService.createOrFindPlayerByName(principal.name)
        val newBoard = player?.let { gameService.createNewBoard(it) }
        return newBoard?.toDto
    }

    @PutMapping("move/{boardId}/{pitId}")
    @Throws(Throwable::class)
    fun move(
        principal: Principal,
        @PathVariable boardId: Long,
        @PathVariable pitId: Long
    ): BoardDto? {
        val player = playerService.createOrFindPlayerByName(principal.name)
        val board = boardService.findBoardById(boardId)
        val startPit = board?.kalahWashers?.stream()?.filter { kalahWasher: KalahWasher -> kalahWasher.id == pitId }?.findAny()
            ?.orElseThrow { PitDoesNotExistException() }
//        if (startPit?.stones == 0) {
//            throw ZeroStonesToMoveException()
//        }
        val boardUpdated = startPit?.let { player?.let { it1 -> gameService.sowStonesFromPit(it1, it as KalahTable, board) } }
        return boardUpdated?.toDto
    }

    @PutMapping("join/{boardId}")
    @Throws(Throwable::class)
    fun join(
        principal: Principal,
        @PathVariable boardId: Long
    ): BoardDto? {
        val player = playerService.createOrFindPlayerByName(principal.name)
        val board = boardService.findBoardById(boardId)
        val boardUpdated = board?.let { player?.let { it1 -> gameService.joinPlayer(it1, it) } }
        return boardUpdated?.toDto
    }

    @GetMapping
    fun getAllBoardsPerPlayer(principal: Principal): MutableList<BoardDto>? {
        val player = playerService.createOrFindPlayerByName(principal.name)
        return player?.let { boardService.findBoardsByPlayer(it) }
    }

    @get:GetMapping("available")
    val availableBoards: List<BoardDto>?
        get() = boardService.findAvailableBoards()
}