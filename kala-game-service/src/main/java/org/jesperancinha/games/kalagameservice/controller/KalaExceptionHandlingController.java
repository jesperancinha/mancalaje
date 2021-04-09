package org.jesperancinha.games.kalagameservice.controller;

import org.jesperancinha.games.kalagameservice.exception.BoardDoesNotExistException;
import org.jesperancinha.games.kalagameservice.exception.GameOverException;
import org.jesperancinha.games.kalagameservice.exception.InvalidPitException;
import org.jesperancinha.games.kalagameservice.exception.NotOwnedPitException;
import org.jesperancinha.games.kalagameservice.exception.PlayerNotJoinedYetException;
import org.jesperancinha.games.kalagameservice.exception.WrongTurnException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.jesperancinha.games.kalagameservice.exception.KalahErrorCode.BOARD_NOT_FOUND;
import static org.jesperancinha.games.kalagameservice.exception.KalahErrorCode.GAME_OVER;
import static org.jesperancinha.games.kalagameservice.exception.KalahErrorCode.INVALID_PIT;
import static org.jesperancinha.games.kalagameservice.exception.KalahErrorCode.NOT_JOINED;
import static org.jesperancinha.games.kalagameservice.exception.KalahErrorCode.NOT_OWNED;
import static org.jesperancinha.games.kalagameservice.exception.KalahErrorCode.WRONG_TURN;

@ControllerAdvice
public class KalaExceptionHandlingController {

    /**
     * The game is over and that means that there is a winner already
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(GameOverException.class)
    public final ResponseEntity<String> handleErrorGameOver(HttpServletRequest req, Exception ex) {
        return ResponseEntity.badRequest().body(GAME_OVER.toString());
    }

    /**
     * We cannot pick Kalah pits to start from
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidPitException.class)
    public final ResponseEntity<String> handleErrorInvalidPit(HttpServletRequest req, Exception ex) {
        return ResponseEntity.badRequest().body(INVALID_PIT.toString());
    }

    /**
     * The player does not own the pit
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(NotOwnedPitException.class)
    public final ResponseEntity<String> handleErrorNotOwned(HttpServletRequest req, Exception ex) {
        return ResponseEntity.badRequest().body(NOT_OWNED.toString());
    }


    /**
     * The player tried to make a move when it wasn't their turn
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(WrongTurnException.class)
    public final ResponseEntity<String> handleErrorWrongTurn(HttpServletRequest req, Exception ex) {
        return ResponseEntity.badRequest().body(WRONG_TURN.toString());
    }

    /**
     * The game cannot start until player two joins in
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(PlayerNotJoinedYetException.class)
    public final ResponseEntity<String> handleErrorPlayerNotJoined(HttpServletRequest req, Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_JOINED.toString());
    }

    /**
     * The Board does not exist
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(BoardDoesNotExistException.class)
    public final ResponseEntity<String> handleErrorBoardDoesNotExist(HttpServletRequest req, Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BOARD_NOT_FOUND.toString());
    }
}