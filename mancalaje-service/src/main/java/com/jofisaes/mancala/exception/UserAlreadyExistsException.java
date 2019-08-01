package com.jofisaes.mancala.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("This user already exists. Try a different email!");
    }
}
