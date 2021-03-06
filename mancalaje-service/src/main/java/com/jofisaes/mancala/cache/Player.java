package com.jofisaes.mancala.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonIgnore
    private Player opponent;

    @JsonIgnore
    private BoardManager boardManager;

    @JsonIgnore
    private List<Hole> allPlayerHoles;

    @JsonIgnore
    private Store playerStore;

    @JsonProperty("opponentName")
    public String getOpponentName() {
        if (Objects.nonNull(this.opponent)) {
            return this.opponent.getName();
        }
        return null;
    }

    public void setHoles(List<Hole> allPlayerHoles, Store playerStore) {
        this.allPlayerHoles = allPlayerHoles;
        this.playerStore = playerStore;
    }

    public void reset() {
        this.setBoardManager(null);
        this.setHoles(null, null);
    }

}
