package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.game.BoardManager;
import com.jofisaes.mancala.game.RoomsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Map;

@Service
@ApplicationScope
public class GameManagerService {


    private RoomsManager roomsManager = new RoomsManager();

    public BoardManager createBoard(Player player, String boardName) {
        Long highestId = roomsManager.getBoardManagerMap().keySet().stream().max(Long::compare).orElse(0L) + 1;
        BoardManager board = BoardManager.create(player, highestId,boardName);
        roomsManager.getBoardManagerMap().put(highestId, board);
        roomsManager.getBoardManagers().add(board);
        return board;
    }

    public BoardManager listPlayerGame(Player player) {
        return player.getBoardManager();
    }


    public RoomsManager listAllGames() {
        return roomsManager;
    }

    public BoardManager joinPlayer(Long boardManagerId, Player player2) {
        BoardManager boardManager = roomsManager.getBoardManagerMap().get(boardManagerId);
        boardManager.setPlayer2(player2);
        return boardManager;
    }

    public void swayStonesFromHole(Player sessionUser, Integer holeId) {
        sessionUser.getBoardManager().swayStonesFromHole(sessionUser, holeId);
    }

    public BoardManager removeRoom(Long roomId, Player sessionUser) {
        Map<Long, BoardManager> boardManagerMap = roomsManager.getBoardManagerMap();
        BoardManager room = boardManagerMap.get(roomId);
        if(room.getBoard().getPlayer1().getName().equals(sessionUser.getName())){
           return roomsManager.removeRoom(roomId);
        }
        return null;
    }
}
