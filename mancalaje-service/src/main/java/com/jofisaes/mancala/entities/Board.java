package com.jofisaes.mancala.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private String name;

    private Player player1;

    private Player player2;

    private List<Hole> allHoles;

    private List<Hole> allPlayerOneHoles;

    private List<Hole> allPlayerTwoHoles;

    private Store playerOneStore;

    private Store playerTwoStore;

    private Player winner;
}
