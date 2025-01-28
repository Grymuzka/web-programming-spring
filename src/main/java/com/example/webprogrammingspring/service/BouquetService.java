package com.example.webprogrammingspring.service;

import com.example.webprogrammingspring.entity.Bouquet;
import com.example.webprogrammingspring.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BouquetService {

    private final static double BASE_PRICE_PER_FLOWER = 5.0;
    private final static double BASE_PRICE_PER_ACCESSORY = 5.0;

    public void setBouquetDetails(Bouquet bouquet, Order order) {
        bouquet.setOrder(order);
        bouquet.setPrice(calculatePrice(bouquet));
    }

    private double calculatePrice(Bouquet bouquet) {
        return bouquet.getFlowerCount() * BASE_PRICE_PER_FLOWER + BASE_PRICE_PER_ACCESSORY;
    }
}
