package com.jofisaes.mancala.exception;

public class NoRoomNameException extends RuntimeException {
    public NoRoomNameException() {
        super("Room must have a name!");
    }
}
