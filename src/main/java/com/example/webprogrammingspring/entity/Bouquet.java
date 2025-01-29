package com.example.webprogrammingspring.entity;

import com.example.webprogrammingspring.type.AccessoryType;
import com.example.webprogrammingspring.type.FlowerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Bouquet extends AuditEntity {

    @NotNull(message = "Cena nie może być pusta")
    @PositiveOrZero(message = "Cena musi być większa niż 0")
    private Double price;

    @NotNull(message = "Typ kwiatów jest wymagany")
    @Enumerated(EnumType.STRING)
    private FlowerType flowerType;

    @NotNull(message = "Typ akcesoriów jest wymagany")
    @Enumerated(EnumType.STRING)
    private AccessoryType accessoryType;

    @NotNull(message = "Liczba kwiatów nie może być pusta")
    @Min(value = 1, message = "Bukiet musi zawierać przynajmniej 1 kwiat")
    @Max(value = 100, message = "Bukiet nie może zawierać więcej niż 100 kwiatów")
    private Integer flowerCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @NotNull(message = "Bukiet musi być przypisany do zamówienia")
    private Order order;
}
