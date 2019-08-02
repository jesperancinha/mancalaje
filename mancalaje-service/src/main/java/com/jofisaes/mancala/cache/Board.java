package com.jofisaes.mancala.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class Board implements Serializable {

    private static Predicate<Hole> holePredicate = hole -> hole.getStones() == 0;

    @JsonProperty("name")
    private String name;

    @JsonProperty("player1")
    private Player player1;

    @JsonProperty("player2")
    private Player player2;

    @JsonProperty("allHoles")
    private List<Hole> allHoles;

    @JsonIgnore
    private List<Hole> allPlayerOneHoles;

    @JsonIgnore
    private List<Hole> allPlayerTwoHoles;

    @JsonIgnore
    private Store playerOneStore;

    @JsonIgnore
    private Store playerTwoStore;

    private Player winner;

    public Board(String boardName) {
        this.name = boardName;
        this.allHoles = new ArrayList<>();
        calculateBoard();
        calculateNextHoles();
        calculateOppositeHoles();
    }

    /**
     * calculates the board iself
     */
    @JsonIgnore
    private void calculateBoard() {
        allPlayerOneHoles = IntStream.range(0, 6).boxed().map(id -> new Hole(player1, id)).collect(Collectors.toList());
        this.playerOneStore = new Store(player1, 6);
        allPlayerTwoHoles = IntStream.range(7, 13).boxed().map(id -> new Hole(player2, id))
                .collect(Collectors.toList());
        this.playerTwoStore = new Store(player2, 13);
        allHoles.addAll(allPlayerOneHoles);
        allHoles.add(playerOneStore);
        allHoles.addAll(allPlayerTwoHoles);
        allHoles.add(playerTwoStore);
    }

    @JsonIgnore
    public void initializePlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.allPlayerOneHoles.forEach(hole -> hole.setPlayer(player1));
        this.allPlayerTwoHoles.forEach(hole -> hole.setPlayer(player2));
        this.playerOneStore.setPlayer(player1);
        this.playerTwoStore.setPlayer(player2);
    }

    /**
     * calculates all next holes
     */
    @JsonIgnore
    private void calculateNextHoles() {
        List<Hole> nextHoleArrayList = new ArrayList<>();
        nextHoleArrayList.add(allHoles.get(allHoles.size() - 1));
        nextHoleArrayList.addAll(allHoles.subList(1, allHoles.size()));
        IntStream.range(0, 13).boxed().forEach(index -> allHoles.get(index).setNextHole(nextHoleArrayList.get(index + 1)));
        allHoles.get(13).setNextHole(allHoles.get(0));
    }

    /**
     * Calculates opposite holes
     */
    @JsonIgnore
    private void calculateOppositeHoles() {
        IntStream.range(0, 6).boxed().forEach(index -> {
            allPlayerOneHoles.get(index).setOppositeHole(allPlayerTwoHoles.get(5 - index));
            allPlayerTwoHoles.get(5 - index).setOppositeHole(allPlayerOneHoles.get(index));
        });
    }

    public void setPlayer1(@NonNull Player player1) {
        player1.setHoles(this.allPlayerOneHoles, this.playerOneStore);
        this.player1 = player1;
    }

    public void setPlayer2(@NonNull Player player2) {
        player2.setHoles(this.allPlayerTwoHoles, this.playerTwoStore);
        this.player2 = player2;
    }

    @VisibleForTesting
    synchronized Hole swayStonseFromHole(Player currentPlayer, Hole hole, Integer stones) {
        if (stones == 1 && hole.getStones() == 0 && hole.getPlayer() == currentPlayer && !(hole instanceof Store)) {
            int fetchedStones = 1 + hole.getOppositeHole().getStones();
            hole.setStones(0);
            hole.getOppositeHole().setStones(0);
            hole.setPickedUpStones(fetchedStones);
            return hole;
        }

        if (hole instanceof Store && !hole.getPlayer().getEmail().equals(currentPlayer.getEmail())) {
            return swayStonseFromHole(currentPlayer, hole.getNextHole(), stones);
        }

        hole.addOne();

        if (stones == 1) {
            return hole;
        } else {
            return swayStonseFromHole(currentPlayer, hole.getNextHole(), stones - 1);
        }

    }

    public boolean isGameOver() {
        boolean playerOneWinner = allPlayerOneHoles.stream().allMatch(holePredicate);
        boolean playerTwoWinner = allPlayerTwoHoles.stream().allMatch(holePredicate);
        if (playerOneWinner) {
            this.winner = this.player1;
        } else if (playerTwoWinner) {
            this.winner = this.player2;
        }
        return playerOneWinner
                || playerTwoWinner;
    }

    public Player removePlayer(String email) {
        if (Objects.nonNull(player1) && email.equalsIgnoreCase(player1.getEmail())) {
            if (Objects.nonNull(player2)) {
                player2.setOpponent(null);
            }
            Player player1 = this.player1;
            this.player1 = null;
            return player1;
        } else if (Objects.nonNull(player2) && email.equalsIgnoreCase(player2.getEmail())) {
            if (Objects.nonNull(player1)) {
                player1.setOpponent(null);
            }
            Player player2 = this.player2;
            this.player2 = null;
            return player2;
        }
        return null;
    }

    public boolean isReady() {
        return Objects.nonNull(player1) && Objects.nonNull(player2);
    }

}
