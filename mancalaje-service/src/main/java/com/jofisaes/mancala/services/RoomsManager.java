package com.jofisaes.mancala.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jofisaes.mancala.game.BoardManager;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Service
public class RoomsManager {

    private final List<BoardManager> boardManagers;

    @JsonIgnore
    private final Map<Long, BoardManager> boardManagerMap;

    public RoomsManager() {
        this.boardManagerMap = new HashMap<>();
        this.boardManagers = new ArrayList<>();
    }

    BoardManager removeRoom(Long roomId) {
        BoardManager boardManager = boardManagerMap.get(roomId);
        boardManagers.remove(boardManager);
        return boardManagerMap.remove(roomId);
    }

    void forceRemoveRoom(Long roomId) {
        boardManagers.removeIf(room -> room.getBoardManagerId().equals(roomId));
    }
}
