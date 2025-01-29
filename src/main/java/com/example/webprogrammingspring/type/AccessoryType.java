package com.example.webprogrammingspring.type;

import lombok.Getter;

@Getter
public enum AccessoryType {
    RIBBON("Wstążka", 2.0),
    CARD("Kartka", 1.5),
    BOW("Kokarda", 3.0),
    WRAPPING_PAPER("Papier ozdobny", 2.5),
    GLITTER("Brokat", 1.8),
    TEDDY_BEAR("Miś", 10.0),
    BALLOON("Balon", 5.0),
    CHOCOLATES("Czekoladki", 8.0);

    private final String name;
    private final double price;

    AccessoryType(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
