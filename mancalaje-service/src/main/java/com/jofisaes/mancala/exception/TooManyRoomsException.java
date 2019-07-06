package com.jofisaes.mancala.exception;

public class TooManyRoomsException extends RuntimeException{
    public TooManyRoomsException(int maxRooms) {
        super(String.format("Maximum room number limit is: %d", maxRooms));
    }
}
