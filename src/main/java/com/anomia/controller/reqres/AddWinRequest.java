package com.anomia.controller.reqres;

import lombok.Getter;

public class AddWinRequest {
    @Getter
    private int loseId;

    public AddWinRequest() {}
    public AddWinRequest(int loseId) { this.loseId = loseId; }
}
