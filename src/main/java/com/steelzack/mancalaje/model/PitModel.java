package com.steelzack.mancalaje.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
@JsonAutoDetect
public class PitModel {
    private Integer nStones;

    private String keyName;

    // 0 - No click
    // 1 - Can click
    // 2 - forbidden
    private int clickMode;

    public PitModel(Integer nStones, String keyName, int clickMode) {
        this.nStones = nStones;
        this.keyName = keyName;
        this.clickMode = clickMode;
    }

    public Integer getnStones() {
        return nStones;
    }

    public String getKeyName() {
        return keyName;
    }

    public int getClickMode() {
        return clickMode;
    }
}
