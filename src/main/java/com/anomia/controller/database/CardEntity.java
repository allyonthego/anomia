package com.anomia.controller.database;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class CardEntity {
    @Getter
    @Id
    private int id;
    @Getter
    private String colour;
    @Getter
    private String word;

    public CardEntity() {}
    public CardEntity(int id, String colour, String word) {
        this.id = id;
        this.colour = colour;
        this.word = word;
    }
}
