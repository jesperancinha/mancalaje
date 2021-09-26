package org.jesperancinha.games.kalagameservice.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.jesperancinha.games.kalagameservice.service.GameService
import org.jesperancinha.games.kalagameservice.service.BoardService
import org.jesperancinha.games.kalagameservice.service.PlayerService
import org.springframework.web.bind.annotation.PutMapping
import kotlin.Throws
import java.security.Principal
import org.springframework.web.bind.annotation.GetMapping
import org.jesperancinha.games.kalagameservice.dto.BoardDto
import org.jesperancinha.games.kalagameservice.dto.converters.BoardConverter
import org.springframework.web.bind.annotation.PostMapping
import org.jesperancinha.games.kalagameservice.model.Player
import org.jesperancinha.games.kalagameservice.model.Board
import org.springframework.web.bind.annotation.PathVariable
import org.jesperancinha.games.kalagameservice.model.Pit
import org.jesperancinha.games.kalagameservice.exception.PitDoesNotExistException
import org.jesperancinha.games.kalagameservice.exception.ZeroStonesToMoveException
import java.util.function.Supplier

@RestController
@RequestMapping("api")
class KalaGameController(
    private val gameService: GameService,
    private val boardService: BoardService, private val playerService: PlayerService
) {
    @PutMapping("leave")
    @Throws(Throwable::class)
    fun leavegae(principal: Principal) {
        playerService.leaveCurrentGame(principal.name)
    }

    @GetMapping("current")
    @Throws(Throwable::class)
    fun getCurrentBoard(principal: Principal): BoardDto? {
        return BoardConverter.toDto(
            playerService.createOrFindPlayerByName(principal.name)?.currentBoard
        )
    }

    @PostMapping("create")
    fun createBoard(principal: Principal): BoardDto? {
        val player = playerService.createOrFindPlayerByName(principal.name)
        val newBoard = player?.let { gameService.createNewBoard(it) }
        return BoardConverter.toDto(newBoard)
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
        val startPit = board?.pits?.stream()?.filter { pit: Pit -> pit.id == pitId }?.findAny()?.orElseThrow { PitDoesNotExistException() }
        if (startPit?.stones == 0) {
            throw ZeroStonesToMoveException()
        }
        val boardUpdated = startPit?.let { player?.let { it1 -> gameService.sowStonesFromPit(it1, it, board) } }
        return BoardConverter.toDto(boardUpdated)
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
        return BoardConverter.toDto(boardUpdated)
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