package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;

import java.util.Objects;

public class Mappings {
    private static final String MANCALA = "/mancala/";
    static final String MANCALA_ACTIONS = MANCALA + "actions";
    static final String MANCALA_BOARDS = MANCALA + "boards";
    static final String MANCALA_ROOMS = MANCALA + "rooms";
    static final String MANCALA_USERS = MANCALA + "users";


    public static boolean playerMatch(Player playerA, Player playerB) {
        return Objects.nonNull(playerB) && Objects.nonNull(playerA) && playerA.getEmail().equals(playerB.getEmail());
    }
}
