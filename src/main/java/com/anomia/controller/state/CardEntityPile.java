package com.anomia.controller.state;

import com.anomia.controller.database.CardEntity;

import java.util.Arrays;
import java.util.Stack;

// refactor to list it out only once
public class CardEntityPile {
    public static Stack<CardEntity> createCardEntityPile(int gameId) {
        CardEntity[] pile = {
                new CardEntity(gameId,"BLUE", "Clothing"),
                new CardEntity(gameId,"YELLOW", "Reality TV Show"),
                new CardEntity(gameId,"BLUE", "Keyboard Key"),
                new CardEntity(gameId,"GREEN", "Pasta"),
                new CardEntity(gameId,"RED", "Month"),
                new CardEntity(gameId,"RED", "Plant"),
                new CardEntity(gameId,"YELLOW", "Radio Station"),
                new CardEntity(gameId,"GREEN", "Reptile"),
                new CardEntity(gameId,"GREEN", "Restaurant"),
                new CardEntity(gameId,"BLUE", "Dinosaur"),
                new CardEntity(gameId,"YELLOW", "Male tennis player"),
                new CardEntity(gameId,"RED", "Astronaut"),
                new CardEntity(gameId,"GREEN", "Breakfast"),
                new CardEntity(gameId,"ORANGE", "Bean"),
                new CardEntity(gameId,"YELLOW", "Hat"),
                new CardEntity(gameId,"ORANGE", "Rock Opera"),
                new CardEntity(gameId,"RED", "Sitcom"),
                new CardEntity(gameId,"BLUE", "Vegetable"),
                new CardEntity(gameId,"GREEN", "Hot Drink"),
                new CardEntity(gameId,"YELLOW", "Palindrome"),
        };
        Stack<CardEntity> stack = new Stack<>();
        stack.addAll(Arrays.asList(pile));
        return stack;
    }

    // testing
    public static Stack<Card> createCardPile() {
        Stack<Card> stack = new Stack<>();
        Stack<CardEntity> entityStack = createCardEntityPile(1);
        int i = 1;
        while(!entityStack.isEmpty()) {
            stack.push(new Card(i,entityStack.pop()));
            ++i;
        }
        return stack;
    }
}
