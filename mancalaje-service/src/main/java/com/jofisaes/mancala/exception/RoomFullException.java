package com.jofisaes.mancala.exception;

public class RoomFullException extends RuntimeException {
    public RoomFullException() {
        super("You can only have two players per room!");
    }
}
