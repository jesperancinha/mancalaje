package com.jofisaes.mancala.entities;

import com.jofisaes.mancala.game.BoardManager;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Serializable {

    private String name;

    private String email;

    private Player opponent;

    private BoardManager boardManager;

    private List<Hole> allPlayerHoles;

    private Store playerStore;
}
