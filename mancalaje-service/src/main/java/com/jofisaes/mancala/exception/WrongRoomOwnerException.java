package com.jofisaes.mancala.exception;

public class WrongRoomOwnerException extends RuntimeException {
    public WrongRoomOwnerException(){
        super("You are not the owner of this room!");
    }
}
