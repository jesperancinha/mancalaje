package com.jofisaes.mancala.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

    private static Predicate<Hole> holePredicate = hole -> hole.getStones() == 0;

    @JsonProperty("player1")
    private Player player1;

    @JsonProperty("player2")
    private Player player2;

    @JsonProperty("allHoles")
    private List<Hole> allHoles = new ArrayList<>();

    @JsonIgnore
    private List<Hole> allPlayerOneHoles;

    @JsonIgnore
    private List<Hole> allPlayerTwoHoles;

    @JsonIgnore
    private Store playerOneStore;

    @JsonIgnore
    private Store playerTwoStore;

    public Board() {
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

    /**
     * calculates all next holes
     */
    @JsonIgnore
    private void calculateNextHoles() {
        List<Hole> nextHoleArrayList = new ArrayList<>();
        nextHoleArrayList.add(allHoles.get(allHoles.size() - 1));
        nextHoleArrayList.addAll(allHoles.subList(1, allHoles.size()));
        IntStream.range(0, 13).boxed().forEach(index -> allHoles.get(index).setNextHole(nextHoleArrayList.get(index)));
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

    public void setPlayer1(Player player1) {
        player1.setHoles(this.allPlayerOneHoles, this.playerOneStore);
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        player2.setHoles(this.allPlayerTwoHoles, this.playerTwoStore);
        this.player2 = player2;
    }

    public Hole swayStonseFromHole(Player currentPlayer, Hole hole, Integer stones) {
        if (stones == 1 && hole.getStones() == 0 && hole.getPlayer() == currentPlayer) {
            hole.setPickedUpStones(hole.getOppositeHole().pickStones() + 1);
            return hole;
        }
        hole.addOne();
        if (stones == 1) {
            return hole;
        }
        if ((hole == playerTwoStore || hole == playerOneStore)
            && hole.getPlayer() != currentPlayer) {
            return swayStonseFromHole(currentPlayer, hole.getOppositeHole(), stones--);
        } else {
            return swayStonseFromHole(currentPlayer, hole.getNextHole(), stones--);
        }

    }

    public boolean isGameOver() {
        return allPlayerOneHoles.stream().allMatch(holePredicate)
            && allPlayerTwoHoles.stream().allMatch(holePredicate);
    }
}
