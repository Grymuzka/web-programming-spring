package com.example.webprogrammingspring.entity;

import com.example.webprogrammingspring.type.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
public class Order extends AuditEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bouquet_id")
    private Bouquet bouquet;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
