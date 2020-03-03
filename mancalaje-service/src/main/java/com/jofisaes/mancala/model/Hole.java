package com.jofisaes.mancala.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hole")
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
