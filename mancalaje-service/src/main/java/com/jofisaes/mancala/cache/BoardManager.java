package com.jofisaes.mancala.cache;

import static com.jofisaes.mancala.services.Validator.playerMatch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.Semaphore;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardManager implements Serializable {

    @JsonProperty("currentPlayer")
    private Player currentPlayer;

    @JsonProperty("winner")
    private Player winner;

    @JsonProperty("owner")
    private Player owner;

    @JsonProperty("board")
    private Board board;

    @JsonProperty("boardManagerId")
    private Long boardManagerId;

    @JsonProperty("gameover")
    private boolean gameOver;

    private Semaphore semaphore = new Semaphore(1);

    private BoardManager(Long boardManagerId) {
        this.boardManagerId = boardManagerId;
    }

    public static BoardManager create(Player player, Long boardManagerId, String boardName) {
        BoardManager boardManager = new BoardManager(boardManagerId);
        Board board = new Board(boardName);
        player.setBoardManager(boardManager);
        boardManager.setBoard(board);
        boardManager.setOwner(player);
        return boardManager;
    }

    @JsonIgnore
    public void setPlayer1(Player player1) {
        this.board.setPlayer1(player1);
    }

    @JsonIgnore
    public void setPlayer2(Player player2) {
        this.board.setPlayer2(player2);
    }

    @JsonIgnore
    public synchronized void swayStonesFromHole(Player sessionUser, Integer holeId) throws InterruptedException {
        semaphore.acquire();
        Hole hole = getPlayHoleIFValid(sessionUser, holeId);
        if (Objects.nonNull(hole)) {
            swayStonesFromHole(sessionUser, hole);
        }
        semaphore.release();
    }

    private void swayStonesFromHole(Player sessionUser, Hole hole) {
        Integer stones = hole.pickStones();
        Hole lastHole = board.swayStonseFromHole(sessionUser, hole.getNextHole(), stones);
        if (lastHole.getPickedUpStones() > 0) {
            sessionUser.getPlayerStore().addStones(lastHole.flushPickedUpStones());
        }
        this.gameOver = this.board.isGameOver();
        if (!this.gameOver) {
            if (lastHole.getStones() > 1 && lastHole != sessionUser.getPlayerStore()) {
                this.switchCurrentPlayer();
            }
        } else {
            this.winner = this.board.getWinner();
            this.currentPlayer = this.winner;
        }
    }

    private Hole getPlayHoleIFValid(Player sessionUser, int holeId) {
        if (!this.gameOver && currentPlayer.getEmail().equalsIgnoreCase(sessionUser.getEmail())) {
            final Hole hole = board.getAllHoles().get(holeId);
            if (hole.getStones() > 0 && sessionUser.getAllPlayerHoles().contains(hole) && !(hole instanceof Store)) {
                return hole;
            }
        }
        return null;
    }

    private void switchCurrentPlayer() {
        if (this.currentPlayer == this.board.getPlayer1()) {
            this.currentPlayer = this.board.getPlayer2();
        } else if (this.currentPlayer == this.board.getPlayer2()) {
            this.currentPlayer = this.board.getPlayer1();
        }
    }

    public Player refreshSessionUser(Player sessionUser) {
        if (playerMatch(sessionUser, getBoard().getPlayer1())) {
            return getBoard().getPlayer1();
        }
        if (playerMatch(sessionUser, getBoard().getPlayer2())) {
            return getBoard().getPlayer2();
        }
        return null;
    }
}
