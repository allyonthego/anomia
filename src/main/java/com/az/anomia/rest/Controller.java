package com.az.anomia.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/player")
    public Player getPlayer() {
        return new Player(1);
    }
}
