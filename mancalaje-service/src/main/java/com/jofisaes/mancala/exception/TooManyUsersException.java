package com.jofisaes.mancala.exception;

public class TooManyUsersException extends RuntimeException {
    public TooManyUsersException(int maxUsers) {
        super(String.format("Maximum user number limit is: %d", maxUsers));
    }
}
