package com.jofisaes.mancala.rest;

import com.jofisaes.mancala.entities.Player;

import java.util.Objects;

public class Mappings {
    static final String MANCALA_BOARDS = "mancala/boards";
    static final String MANCALA_ROOMS = "mancala/rooms";
    static final String MANCALA_USERS = "mancala/users";


    public static boolean playerMatch(Player player, Player player1) {
        return Objects.nonNull(player1) && player.getEmail().equals(player1.getEmail());
    }
}
