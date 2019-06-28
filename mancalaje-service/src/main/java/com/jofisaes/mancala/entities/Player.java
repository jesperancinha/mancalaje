package com.jofisaes.mancala.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jofisaes.mancala.game.BoardManager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private BoardManager boardManager;

    @JsonIgnore
    private List<Hole> allPlayerHoles;

    @JsonIgnore
    private Store playerStore;

    public void setHoles(List<Hole> allPlayerHoles, Store playerStore) {
        this.allPlayerHoles = allPlayerHoles;
        this.playerStore = playerStore;
    }
}
