package com.jofisaes.mancala.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hole {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("stones")
    private Integer stones = 4;

    @JsonProperty("player")
    private final Player player;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonIgnore
    private Integer pickedUpStones;

    @JsonIgnore
    private Hole nextHole;

    @JsonIgnore
    private Hole oppositeHole;

    public Hole(Player player, Integer id) {
        this.player = player;
        this.id = id;
    }

    public Integer pickStones() {
        Integer pickedStones = this.stones;
        this.stones = 0;
        return pickedStones;
    }

    void addOne() {
        this.stones++;
    }

    public Integer flushPickedUpStones() {
        Integer pickedStones = this.pickedUpStones;
        this.pickedUpStones = 0;
        return pickedStones;
    }

    protected void addStones(Integer flushPickedUpStones) {
        this.stones += flushPickedUpStones;
    }
}
