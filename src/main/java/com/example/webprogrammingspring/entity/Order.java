package com.example.webprogrammingspring.entity;

import com.example.webprogrammingspring.type.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
public class Order extends AuditEntity {

    @NotNull(message = "Status zamówienia jest wymagany")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Size(min = 1, message = "Zamówienie musi zawierać co najmniej jeden bukiet")
    private List<Bouquet> bouquets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Zamówienie musi być powiązane z klientem")
    private Customer customer;

    @NotNull(message = "Cena całkowita nie może być pusta")
    @PositiveOrZero(message = "Cena całkowita nie może być ujemna")
    private Double totalPrice;
}
