package org.jesperancinha.games.kalagameservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Pit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private PitType pitType;

    @Column
    private Integer stones;

    @OneToOne
    private Pit nextPit;

    @OneToOne
    private Pit oppositePit;

    @OneToOne
    private Player player;
}
