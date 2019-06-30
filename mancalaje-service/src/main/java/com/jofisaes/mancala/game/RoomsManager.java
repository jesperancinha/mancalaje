package com.jofisaes.mancala.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class RoomsManager implements Serializable {

    private List<BoardManager> boardManagers = new ArrayList<>();

    @JsonIgnore
    private Map<Long, BoardManager> boardManagerMap = new HashMap<>();


}
