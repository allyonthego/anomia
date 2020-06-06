package com.anomia.controller.reqres;

import lombok.Getter;

public class StartGameResponse {
    @Getter
    private int gameId;

    public StartGameResponse() {}
    public StartGameResponse(int gameId) {
        this.gameId = gameId;
    }
}
