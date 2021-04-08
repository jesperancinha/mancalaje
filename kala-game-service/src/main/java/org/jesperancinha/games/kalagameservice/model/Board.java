package org.jesperancinha.games.kalagameservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private Long id;

    private List<Pit> pits;

    private Pit pitOne;

    private Pit kalahOne;

    private Player playerOne;

    private Pit pitTwo;

    private Pit kalahTwo;

    private Player playerTwo;

    private Player currentPlayer;
}
