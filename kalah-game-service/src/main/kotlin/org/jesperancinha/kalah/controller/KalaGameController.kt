package org.jesperancinha.kalah.controller

import org.jesperancinha.kalah.dto.BoardDto
import org.jesperancinha.kalah.exception.PitDoesNotExistException
import org.jesperancinha.kalah.model.KalahWasher
import org.jesperancinha.kalah.model.toDto
import org.jesperancinha.kalah.service.KalahBoardService
import org.jesperancinha.kalah.service.KalahGameService
import org.jesperancinha.kalah.service.KalahPlayerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.UUID

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
        @PathVariable boardId: UUID,
        @PathVariable pitId: UUID
    ): BoardDto? {
        val player = playerService.createOrFindPlayerByName(principal.name)
        val board = boardService.findBoardById(boardId)
        val startPit =
            board?.kalahWashers?.stream()?.filter { kalahWasher: KalahWasher -> kalahWasher.id == pitId }?.findAny()
                ?.orElseThrow { PitDoesNotExistException() }
//        if (startPit?.stones == 0) {
//            throw ZeroStonesToMoveException()
//        }
        val boardUpdated = startPit?.let { player?.let { it1 -> gameService.rolloutCupsFromPayersWasherOnBoard(it1, it, board) } }
        return boardUpdated?.toDto
    }

    @PutMapping("join/{boardId}")
    fun join(
        principal: Principal,
        @PathVariable boardId: UUID
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