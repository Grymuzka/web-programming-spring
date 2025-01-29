package com.example.webprogrammingspring.type;

import lombok.Getter;

@Getter
public enum FlowerType {
    ROSE("Róża", 5.0),
    TULIP("Tulipan", 3.5),
    LILY("Lilia", 4.0),
    ORCHID("Orchidea", 7.5),
    SUNFLOWER("Słonecznik", 6.0),
    DAISY("Stokrotka", 2.5),
    PEONY("Piwonia", 8.0),
    CHRYSANTHEMUM("Chryzantema", 4.5),
    CARNATION("Goździk", 3.0),
    HYDRANGEA("Hortensja", 6.5),
    IRIS("Irys", 4.2),
    LAVENDER("Lawenda", 5.8),
    MAGNOLIA("Magnolia", 9.0),
    VIOLET("Fiołek", 2.8);

    private final String name;
    private final double price;

    FlowerType(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
