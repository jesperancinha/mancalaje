package com.jofisaes.mancala.services;

import com.jofisaes.mancala.cache.Player;

import java.util.Objects;

public class Validator {
    public static boolean playerMatch(Player playerA, Player playerB) {
        return Objects.nonNull(playerB) && Objects.nonNull(playerA) && playerA.getEmail().equals(playerB.getEmail());
    }
}
