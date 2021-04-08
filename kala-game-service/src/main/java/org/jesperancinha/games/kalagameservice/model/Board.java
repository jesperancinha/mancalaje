package org.jesperancinha.games.kalagameservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Pit> pits;

    @OneToOne
    private Pit pitOne;

    @OneToOne
    private Pit kalahOne;

    @OneToOne
    private Player playerOne;

    @OneToOne
    private Pit pitTwo;

    @OneToOne
    private Pit kalahTwo;

    @OneToOne
    private Player playerTwo;

    @OneToOne
    private Player currentPlayer;


}
