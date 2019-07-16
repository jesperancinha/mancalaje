package com.jofisaes.mancala.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

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

    @VisibleForTesting
    Hole(Player player, Integer id) {
        this.player = player;
        this.id = id;
        this.stones = 4;
    }

    @PackagePrivate
    @VisibleForTesting
    Integer pickStones() {
        Integer pickedStones = this.stones;
        this.stones = 0;
        return pickedStones;
    }

    @PackagePrivate
    @VisibleForTesting
    void addOne() {
        this.stones++;
    }

    @PackagePrivate
    @VisibleForTesting
    int flushPickedUpStones() {
        int pickedStones = this.pickedUpStones;
        this.pickedUpStones = 0;
        return pickedStones;
    }

    protected void addStones(int flushPickedUpStones) {
        this.stones += flushPickedUpStones;
    }
}
