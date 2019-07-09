package com.jofisaes.mancala.exception;

public class AlreadyInGameException extends RuntimeException {
    public AlreadyInGameException() {
        super("Your user has reportedly not left this room. This could be a system error. Please report it to the developer");
    }

}
