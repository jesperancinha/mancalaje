package com.jofisaes.mancala.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Hole")
public class Hole {

    @Id
    @Column
    private Long id;

    @Column
    private Integer holeId;

    @OneToOne
    @JoinColumn(name = "id")
    private Player player;

    @Column
    private Integer stones;

    @OneToOne
    @JoinColumn(name = "id")
    private Board board;
}
