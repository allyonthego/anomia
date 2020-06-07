package com.anomia.controller.state;

import com.anomia.controller.database.CardEntity;

import java.util.Arrays;
import java.util.Stack;

public class CardEntityPile {
    public static Stack<CardEntity> createCardEntityPile() {
        CardEntity[] pile = {
                new CardEntity(1,"BLUE", "Test"),
                new CardEntity(2,"BLUE", "Test"),
                new CardEntity(3,"BLUE", "Test"),
                new CardEntity(4,"BLUE", "Test"),
                new CardEntity(5,"BLUE", "Test"),
        };
        Stack<CardEntity> stack = new Stack<>();
        stack.addAll(Arrays.asList(pile));
        return stack;
    }
}
