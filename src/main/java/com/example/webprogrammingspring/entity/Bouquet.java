package com.example.webprogrammingspring.entity;

import com.example.webprogrammingspring.type.AccessoryType;
import com.example.webprogrammingspring.type.FlowerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Bouquet extends AuditEntity {

    private Double price;

    @Enumerated(EnumType.STRING)
    private FlowerType flowerType;

    @Enumerated(EnumType.STRING)
    private AccessoryType accessoryType;

    private Integer flowerCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
