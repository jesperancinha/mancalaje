package com.jofisaes.mancala.exception;

public class UserRemovedException extends RuntimeException {
    public UserRemovedException() {
        super("This user has been removed!");
    }
}
