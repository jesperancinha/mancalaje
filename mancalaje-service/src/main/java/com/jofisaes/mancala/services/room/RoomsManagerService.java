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

    @JsonIgnore
    private final Map<Long, BoardManager> boardManagerMap;

    public RoomsManagerService() {
        this.boardManagerMap = new HashMap<>();
    }

    public BoardManager removeRoom(Long roomId) {
        return boardManagerMap.remove(roomId);
    }

    public BoardManager forceRemoveRoom(Long roomId) {
        BoardManager boardManager = boardManagerMap.get(roomId);
        boardManagerMap.remove(roomId);
        return boardManager;
    }

    public List<BoardManager> getBoardManagers() {
        return new ArrayList<>(boardManagerMap.values());
    }

    public Map<Long, BoardManager> getBoardManagerMap() {
        return boardManagerMap;
    }

    public void addBoard(Long highestId, BoardManager board) {
        getBoardManagerMap().put(highestId, board);
        getBoardManagers().add(board);
    }
}
