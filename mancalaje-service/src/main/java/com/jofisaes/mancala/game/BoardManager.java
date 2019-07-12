package com.jofisaes.mancala.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jofisaes.mancala.entities.Board;
import com.jofisaes.mancala.entities.Hole;
import com.jofisaes.mancala.entities.Player;
import com.jofisaes.mancala.entities.Store;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static com.jofisaes.mancala.rest.Mappings.playerMatch;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardManager implements Serializable {

    @JsonIgnore
    private Player currentPlayer;

    @JsonProperty("owner")
    private Player owner;

    @JsonProperty("board")
    private Board board;

    @JsonProperty("boardManagerId")
    private Long boardManagerId;

    @JsonProperty("gameover")
    private boolean gameOver;

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
    public void swayStonesFromHole(Player sessionUser, Integer holeId) {
        if (currentPlayer == sessionUser) {
            final Hole hole = board.getAllHoles().get(holeId);
            if (sessionUser.getAllPlayerHoles().contains(hole) && !(hole instanceof Store)) {
                Integer stones = hole.pickStones();
                Hole lastHole = board.swayStonseFromHole(sessionUser, hole.getNextHole(), stones);
                if (lastHole.getPickedUpStones() > 0) {
                    sessionUser.getPlayerStore().addStones(lastHole.flushPickedUpStones());
                }
                this.gameOver = this.board.isGameOver();
                if (!this.gameOver) {
                    if(lastHole.getStones() > 1 && lastHole != currentPlayer.getPlayerStore()) {
                        this.switchCurrentPlayer();
                    }
                }
            }
        }
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
