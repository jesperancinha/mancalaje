package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.exception.NoRoomNameException;
import com.jofisaes.mancala.exception.TooManyRoomsException;
import com.jofisaes.mancala.game.BoardManager;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Map;

@Service
@ApplicationScope
public class GameManagerService {

    private int maxRooms;

    @Autowired
    private RoomsManager roomsManager;

    public GameManagerService(final @Value("${mancalaje.max-rooms:20}") int maxRooms, final RoomsManager roomsManager) {
        this.maxRooms = maxRooms;
        this.roomsManager = roomsManager;
    }

    public BoardManager createBoard(Player player, String boardName) {
        if (roomsManager.getBoardManagers().size() == maxRooms) {
            throw new TooManyRoomsException(maxRooms);
        }
        if (Strings.isEmpty(boardName)) {
            throw new NoRoomNameException();
        }
        Long highestId = roomsManager.getBoardManagerMap().keySet().stream().max(Long::compare).orElse(0L) + 1;
        BoardManager board = BoardManager.create(player, highestId, boardName);
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
        if (room.getBoard().getPlayer1().getName().equals(sessionUser.getName())) {
            return roomsManager.removeRoom(roomId);
        }
        return null;
    }
}
