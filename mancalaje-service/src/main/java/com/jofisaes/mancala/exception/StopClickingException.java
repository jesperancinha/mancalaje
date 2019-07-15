package com.jofisaes.mancala.exception;

public class StopClickingException extends RuntimeException {
    public StopClickingException() {
        super("Please stop clicking consecutively!");
    }
}
