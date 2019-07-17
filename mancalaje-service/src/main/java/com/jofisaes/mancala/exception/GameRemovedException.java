package com.jofisaes.mancala.exception;

public class GameRemovedException extends RuntimeException {
    public GameRemovedException() {
        super("Sorry, this game has been removed or is not available at the moment. Please try to re-login for one last try. Otherwise you can always start a new game!");
    }
}
