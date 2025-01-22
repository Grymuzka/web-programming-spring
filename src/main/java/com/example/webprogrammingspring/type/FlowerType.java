package com.example.webprogrammingspring.type;

import lombok.Getter;

@Getter
public enum FlowerType {
    ROSE("Róża"),
    TULIP("Tulipan"),
    LILY("Lilia");

    private final String name;

    FlowerType(String name) {
        this.name = name;
    }

}
