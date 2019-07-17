package com.jofisaes.mancala.services.game;

import com.jofisaes.mancala.cache.Board;
import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Hole;
import com.jofisaes.mancala.cache.Player;
import com.jofisaes.mancala.exception.*;
import com.jofisaes.mancala.services.room.RoomsManager;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Map;
import java.util.Objects;

import static com.jofisaes.mancala.services.Validator.playerMatch;

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

    public BoardManager getBoardManagerByRoomnId(Long roomId) {
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
        if (board.isReady()) {
            Player player1 = board.getPlayer1();
            Player player2 = board.getPlayer2();
            player1.setBoardManager(boardManager);
            player2.setBoardManager(boardManager);
            player1.setOpponent(board.getPlayer2());
            player2.setOpponent(board.getPlayer1());
            board.initializePlayers(player1, player2);
            calculateFirstPlayer(boardManager, board);
        }
        return boardManager;
    }

    private void calculateFirstPlayer(BoardManager boardManager, Board board) {
        boardManager.setCurrentPlayer(board.getPlayer1());
    }

    /**
     * Once a player leaves the room, the current player is also not available.
     * Once a new player comes in his player, both players must decide which player starts first to resume the game.
     *
     * @param roomId
     * @param player
     * @return
     */
    public Player leaveRoom(Long roomId, Player player) {
        BoardManager boardManager = roomsManager.getBoardManagerMap().get(roomId);
        if (Objects.isNull(boardManager)) {
            throw new GameRemovedException();
        }
        boardManager.setCurrentPlayer(null);
        Board board = boardManager.getBoard();
        return board.removePlayer(player);
    }

    public void swayStonesFromHole(Player sessionUser, Integer holeId) throws InterruptedException {
        BoardManager boardManager = sessionUser.getBoardManager();
        if (Objects.isNull(boardManager)) {
            throw new StopClickingException();
        }
        boardManager.swayStonesFromHole(sessionUser, holeId);
    }

    public BoardManager removeRoom(Long roomId, Player sessionUser) {
        Map<Long, BoardManager> boardManagerMap = roomsManager.getBoardManagerMap();
        BoardManager room = boardManagerMap.get(roomId);
        if (Objects.isNull(room)) {
            return roomsManager.forceRemoveRoom(roomId);
        } else if (playerMatch(room.getOwner(), sessionUser)) {
            return roomsManager.removeRoom(roomId);
        }
        throw new WrongRoomOwnerException();
    }

    public BoardManager handleBoardManager(Long roomId) {
        BoardManager boardManager = this.getBoardManagerByRoomnId(roomId);
        if (Objects.isNull(boardManager)) {
            throw new GameRemovedException();
        }
        Board board = boardManager.getBoard();
        board.getAllPlayerOneHoles().forEach(hole -> enableRightHoles(boardManager, hole));
        board.getAllPlayerTwoHoles().forEach(hole -> enableRightHoles(boardManager, hole));
        return boardManager;
    }

    private void enableRightHoles(BoardManager boardManager, Hole hole) {
        if (Objects.nonNull(hole.getPlayer()) && Objects.nonNull(boardManager.getCurrentPlayer()) && hole.getPlayer() == boardManager.getCurrentPlayer()) {
            hole.setEnabled(true);
        } else {
            hole.setEnabled(false);
        }
    }
}
