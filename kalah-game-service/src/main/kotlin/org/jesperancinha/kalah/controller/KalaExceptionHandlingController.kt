package org.jesperancinha.kalah.controller;

import jakarta.servlet.http.HttpServletRequest
import org.jesperancinha.kalah.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class KalaExceptionHandlingController {
    /**
     * The game is over and that means that there is a winner already
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(GameOverException::class)
    fun handleErrorGameOver(req: HttpServletRequest?, ex: Exception?): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(KalahErrorCode.GAME_OVER.toString())
    }

    /**
     * We cannot pick Kalah pits to start from
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidPitException::class)
    fun handleErrorInvalidPit(req: HttpServletRequest?, ex: Exception?): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(KalahErrorCode.INVALID_PIT.toString())
    }

    /**
     * The player does not own the pit
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(NotOwnedPitException::class)
    fun handleErrorNotOwned(req: HttpServletRequest?, ex: Exception?): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(KalahErrorCode.NOT_OWNED.toString())
    }

    /**
     * The player tried to make a move when it wasn't their turn
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(WrongTurnException::class)
    fun handleErrorWrongTurn(req: HttpServletRequest?, ex: Exception?): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(KalahErrorCode.WRONG_TURN.toString())
    }

    /**
     * The game cannot start until player two joins in
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(PlayerNotJoinedYetException::class)
    fun handleErrorPlayerNotJoined(req: HttpServletRequest?, ex: Exception?): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(KalahErrorCode.NOT_JOINED.toString())
    }

    /**
     * The Board does not exist
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(BoardDoesNotExistException::class)
    fun handleErrorBoardDoesNotExist(req: HttpServletRequest?, ex: Exception?): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(KalahErrorCode.BOARD_NOT_FOUND.toString())
    }

    /**
     * The Pit does not exist
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(PitDoesNotExistException::class)
    fun handleErrorPitDoesNotExist(req: HttpServletRequest?, ex: Exception?): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(KalahErrorCode.PIT_NOT_FOUND.toString())
    }

    /**
     * No Stones to move
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(ZeroStonesToMoveException::class)
    fun handleErrorZeroStonesToMove(req: HttpServletRequest?, ex: Exception?): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(KalahErrorCode.ZERO_STONES.toString())
    }
}