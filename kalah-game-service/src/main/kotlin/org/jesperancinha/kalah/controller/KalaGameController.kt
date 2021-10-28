package org.jesperancinha.kalah.controller

import org.jesperancinha.kalah.dto.BoardDto
import org.jesperancinha.kalah.exception.PitDoesNotExistException
import org.jesperancinha.kalah.model.KalahTable
import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.model.toDto
import org.jesperancinha.kalah.service.KalahBoardService
import org.jesperancinha.kalah.service.KalahGameService
import org.jesperancinha.kalah.service.KalahPlayerService
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
    fun leavegae(principal: Principal) {
        playerService.leaveCurrentGame(principal.name)
    }

    @GetMapping("current")
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