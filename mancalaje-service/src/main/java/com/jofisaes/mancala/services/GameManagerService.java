package com.jofisaes.mancala.services;

import com.jofisaes.mancala.entities.Board;
import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.exception.AlreadyInGameException;
import com.jofisaes.mancala.exception.NoRoomNameException;
import com.jofisaes.mancala.exception.RoomFullException;
import com.jofisaes.mancala.exception.TooManyRoomsException;
import com.jofisaes.mancala.game.BoardManager;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Map;
import java.util.Objects;

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

    public BoardManager getBoardManagerByRoomnId(Long roomId){
        return roomsManager.getBoardManagerMap().get(roomId);
    }

    public RoomsManager listAllGames() {
        return roomsManager;
    }

    public BoardManager joinPlayer(Long boardManagerId, Player player) {
        BoardManager boardManager = roomsManager.getBoardManagerMap().get(boardManagerId);
        Board board = boardManager.getBoard();
        if (Objects.isNull(board.getPlayer1())) {
            boardManager.setPlayer1(player);
        } else if (Objects.isNull(board.getPlayer2()) && !board.getPlayer1().getEmail().equals(player.getEmail())) {
            boardManager.setPlayer2(player);
        } else if (Objects.isNull(board.getPlayer2())) {
            throw new AlreadyInGameException();
        } else throw new RoomFullException();
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
