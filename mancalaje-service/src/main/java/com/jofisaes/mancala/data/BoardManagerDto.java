package com.jofisaes.mancala.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jofisaes.mancala.cache.BoardManager;
import com.jofisaes.mancala.cache.Player;
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
