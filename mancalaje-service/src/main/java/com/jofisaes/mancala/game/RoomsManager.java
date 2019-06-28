package com.jofisaes.mancala.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoomsManager implements Serializable {

    private List<BoardManager> boardManagers = new ArrayList<>();


}
