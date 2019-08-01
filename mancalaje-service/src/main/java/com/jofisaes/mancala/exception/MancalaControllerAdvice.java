package com.jofisaes.mancala.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class MancalaControllerAdvice {

    @ExceptionHandler(value = TooManyRoomsException.class)
    public ResponseEntity<Object> tooManyRoomsException(TooManyRoomsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = TooManyUsersException.class)
    public ResponseEntity<Object> tooManyUsersException(TooManyUsersException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = NoRoomNameException.class)
    public ResponseEntity<Object> noRoomNameException(NoRoomNameException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = RoomFullException.class)
    public ResponseEntity<Object> roomFullException(RoomFullException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = AlreadyInGameException.class)
    public ResponseEntity<Object> aleradyInGameException(AlreadyInGameException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = WrongRoomOwnerException.class)
    public ResponseEntity<Object> wrongRoonOwnerException(WrongRoomOwnerException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = StopClickingException.class)
    public ResponseEntity<Object> stopClickingSoMuchException(StopClickingException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = GameRemovedException.class)
    public ResponseEntity<Object> gameRemovedException(GameRemovedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = UserRemovedException.class)
    public ResponseEntity<Object> userRemovedException(UserRemovedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = RegistrationMailNotSentException.class)
    public ResponseEntity<Object> mailNotSentException(RegistrationMailNotSentException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValid(MethodArgumentNotValidException exception) {
        final FieldError fieldError = exception.getBindingResult().getFieldError();
        final String message = getMessage(fieldError);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(message));
    }

    private String getMessage(FieldError fieldError) {
        if (Objects.isNull(fieldError)) {
            return "Invalid value!";
        }
        return fieldError.getDefaultMessage();
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> methodArgumentNotValid(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
    }
}
