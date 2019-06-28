package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@ApplicationScope
public class GameManagerService {

    private Map<Long, BoardManager> boardManagerMap = new HashMap<>();

    public void createBoard(Player player) {
        Long highestId = boardManagerMap.keySet().stream().max(Long::compare).orElse(0L) + 1;
        BoardManager board = BoardManager.create(player, highestId);
        boardManagerMap.put(highestId, board);
    }

    public Collection<BoardManager> listAllGames() {
        return boardManagerMap.values();
    }

    public BoardManager joinPlayer(Long boardManagerId, Player player2) {
        BoardManager boardManager = boardManagerMap.get(boardManagerId);
        boardManager.setPlayer2(player2);
        return boardManager;
    }

    public void swayStonesFromHole(Player sessionUser, Integer holeId) {
        sessionUser.getBoardManager().swayStonesFromHole(sessionUser, holeId);
    }
}
