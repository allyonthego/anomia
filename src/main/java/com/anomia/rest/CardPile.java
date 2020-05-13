package com.anomia.rest;

import java.util.Arrays;
import java.util.Stack;

import static com.anomia.rest.CardColour.BLUE;

public class CardPile {
    public static Stack<Card> createPile() {
        Card[] pile = {
                new Card(BLUE, "Test")
        };
        Stack<Card> stack = new Stack<>();
        stack.addAll(Arrays.asList(pile));
        return stack;
    }
}
