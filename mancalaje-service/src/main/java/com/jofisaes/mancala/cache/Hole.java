package com.jofisaes.mancala.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Hole implements Serializable {

    @JsonProperty("player")
    private Player player;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("stones")
    private Integer stones;
    @JsonProperty("enabled")
    private boolean enabled;

    @JsonIgnore
    private int pickedUpStones;

    @JsonIgnore
    private Hole nextHole;

    @JsonIgnore
    private Hole oppositeHole;

    Hole(Player player, Integer id) {
        this.player = player;
        this.id = id;
        this.stones = 4;
    }

    public Integer pickStones() {
        Integer pickedStones = this.stones;
        this.stones = 0;
        return pickedStones;
    }

    void addOne() {
        this.stones++;
    }

    public int flushPickedUpStones() {
        int pickedStones = this.pickedUpStones;
        this.pickedUpStones = 0;
        return pickedStones;
    }

    protected void addStones(int flushPickedUpStones) {
        this.stones += flushPickedUpStones;
    }
}
