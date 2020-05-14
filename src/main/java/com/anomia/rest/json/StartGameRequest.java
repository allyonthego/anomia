package com.anomia.rest.json;

import lombok.Getter;

public class StartGameRequest {
    @Getter
    private int numPlayers;

    public StartGameRequest() {}
    public StartGameRequest(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
