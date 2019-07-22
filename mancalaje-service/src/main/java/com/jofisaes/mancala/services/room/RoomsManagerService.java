package com.jofisaes.mancala.services.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jofisaes.mancala.cache.BoardManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomsManagerService {

    private final List<BoardManager> boardManagers;

    @JsonIgnore
    private final Map<Long, BoardManager> boardManagerMap;

    public RoomsManagerService() {
        this.boardManagerMap = new HashMap<>();
        this.boardManagers = new ArrayList<>();
    }

    public BoardManager removeRoom(Long roomId) {
        BoardManager boardManager = boardManagerMap.get(roomId);
        boardManagers.remove(boardManager);
        return boardManagerMap.remove(roomId);
    }

    public BoardManager forceRemoveRoom(Long roomId) {
        BoardManager boardManager = boardManagerMap.get(roomId);
        boardManagers.removeIf(room -> room.getBoardManagerId().equals(roomId));
        return boardManager;
    }

    public List<BoardManager> getBoardManagers() {
        return boardManagers;
    }

    public Map<Long, BoardManager> getBoardManagerMap() {
        return boardManagerMap;
    }
}
