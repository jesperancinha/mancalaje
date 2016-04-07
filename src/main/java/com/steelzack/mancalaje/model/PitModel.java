package com.steelzack.mancalaje.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by joaofilipesabinoesperancinha on 03-04-16.
 */
@JsonAutoDetect
public class PitModel {
    private Integer nStones;

    private String keyName;

    public PitModel(Integer nStones, String keyName) {
        this.nStones = nStones;
        this.keyName = keyName;
    }

    public Integer getnStones() {
        return nStones;
    }

    public String getKeyName() {
        return keyName;
    }
}
