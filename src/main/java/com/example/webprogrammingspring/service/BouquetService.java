package com.example.webprogrammingspring.service;

import com.example.webprogrammingspring.entity.Bouquet;
import com.example.webprogrammingspring.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BouquetService {

    public void setBouquetDetails(Bouquet bouquet, Order order) {
        bouquet.setOrder(order);
        bouquet.setPrice(calculatePrice(bouquet));
    }

    private double calculatePrice(Bouquet bouquet) {
        return (bouquet.getFlowerCount() * bouquet.getFlowerType().getPrice()) + bouquet.getAccessoryType().getPrice();
    }
}
