package com.anomia.rest.json;

import lombok.Getter;

public class StartGameResponse {
    @Getter
    private int gameId;

    public StartGameResponse() {}
    public StartGameResponse(int gameId) {
        this.gameId = gameId;
    }
}
