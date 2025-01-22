package com.example.webprogrammingspring.type;

import lombok.Getter;

@Getter
public enum OrderStatus {
    DRAFT("Szkic"),
    NEW("Nowe"),
    CONFIRMED("Zatwierdzone"),
    READY("Gotowe");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }
}
