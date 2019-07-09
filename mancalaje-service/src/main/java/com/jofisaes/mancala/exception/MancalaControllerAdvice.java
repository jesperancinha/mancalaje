package com.jofisaes.mancala.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MancalaControllerAdvice {

    @ExceptionHandler(value = TooManyRoomsException.class)
    public ResponseEntity<Object> tooManyRoomsException(TooManyRoomsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }

    @ExceptionHandler(value = NoRoomNameException.class)
    public ResponseEntity<Object> noRoomNameException(NoRoomNameException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }

    @ExceptionHandler(value = RoomFullException.class)
    public ResponseEntity<Object> roomFullException(RoomFullException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }

    @ExceptionHandler(value = AlreadyInGameException.class)
    public ResponseEntity<Object> aleradyInGame(AlreadyInGameException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }
}
