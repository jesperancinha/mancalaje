package com.jofisaes.mancala.entities;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hole {

    private Player player;

    private Integer id;

    private Integer stones;

    private boolean enabled;

    private int pickedUpStones;

    private Hole nextHole;

    private Hole oppositeHole;
}
