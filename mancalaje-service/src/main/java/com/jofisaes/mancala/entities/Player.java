package com.jofisaes.mancala.entities;

import com.jofisaes.mancala.game.BoardManager;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Player opponent;

    @OneToOne
    @JoinColumn(name = "id")
    private Board board;

    @Column
    private BoardManager boardManager;

    @OneToMany
    private List<Hole> allPlayerHoles;

    @OneToOne
    private Hole playerStore;
}
