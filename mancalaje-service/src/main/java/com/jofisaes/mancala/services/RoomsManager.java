package com.jofisaes.mancala.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jofisaes.mancala.game.BoardManager;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Service
public class RoomsManager implements Serializable {

    private List<BoardManager> boardManagers = new ArrayList<>();

    @JsonIgnore
    private Map<Long, BoardManager> boardManagerMap = new HashMap<>();

    public BoardManager removeRoom(Long roomId) {
        BoardManager boardManager = boardManagerMap.get(roomId);
        boardManagers.remove(boardManager);
        return boardManagerMap.remove(roomId);
    }

}
