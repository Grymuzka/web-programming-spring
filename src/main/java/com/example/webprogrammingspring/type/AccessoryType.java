package com.example.webprogrammingspring.type;

import lombok.Getter;

@Getter
public enum AccessoryType {
    RIBBON("Wstążka"),
    CARD("Kartka");

    private final String name;

    AccessoryType(String name) {
        this.name = name;
    }
}
