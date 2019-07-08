package com.jofisaes.mancala.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jofisaes.mancala.entities.Player;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class BoardManagerDto implements Serializable {

    @JsonProperty("loggedPlayer")
    private Player loggedPlayer;

    @JsonProperty("boardManager")
    private BoardManager boardManager;

}
