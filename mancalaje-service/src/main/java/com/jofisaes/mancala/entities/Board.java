package com.jofisaes.mancala.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "id")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "id")
    private Player player2;

    @Column
    @OneToMany
    private List<Hole> allHoles;
}
