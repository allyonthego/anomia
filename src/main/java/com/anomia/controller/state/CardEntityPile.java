package com.anomia.controller.state;

import com.anomia.controller.database.CardEntity;

import java.util.Arrays;
import java.util.Stack;

import static com.anomia.controller.state.CardColour.BLUE;

public class CardEntityPile {
    // testing
    public static Stack<Card> createCardPile() {
        Card[] pile = {
                new Card(1,BLUE, "Test"),
                new Card(2,BLUE, "Test"),
                new Card(3,BLUE, "Test"),
                new Card(4,BLUE, "Test"),
                new Card(5,BLUE, "Test"),
        };
        Stack<Card> stack = new Stack<>();
        stack.addAll(Arrays.asList(pile));
        return stack;
    }
    public static Stack<CardEntity> createCardEntityPile(int gameId) {
        CardEntity[] pile = {
                new CardEntity(gameId,"BLUE", "Test"),
                new CardEntity(gameId,"BLUE", "Test"),
                new CardEntity(gameId,"BLUE", "Test"),
                new CardEntity(gameId,"BLUE", "Test"),
                new CardEntity(gameId,"BLUE", "Test"),
        };
        Stack<CardEntity> stack = new Stack<>();
        stack.addAll(Arrays.asList(pile));
        return stack;
    }
}
