package com.example.webprogrammingspring.service;

import com.example.webprogrammingspring.entity.Bouquet;
import com.example.webprogrammingspring.repository.BouquetRepository;
import com.example.webprogrammingspring.type.AccessoryType;
import com.example.webprogrammingspring.type.FlowerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BouquetService {

    private final BouquetRepository bouquetRepository;

    private final static double BASE_PRICE_PER_FLOWER = 5.0;
    private final static double BASE_PRICE_PER_ACCESSORY = 5.0;

    public Bouquet saveBouquet(Bouquet bouquet) {
        bouquet.setPrice(calculatePrice(bouquet));
        return bouquetRepository.save(bouquet);
    }

    private double calculatePrice(Bouquet bouquet) {
        return bouquet.getFlowerCount() * BASE_PRICE_PER_FLOWER + BASE_PRICE_PER_ACCESSORY;
    }

    public Bouquet createDefaultBouquet() {
        Bouquet bouquet = new Bouquet();
        bouquet.setFlowerType(FlowerType.ROSE);
        bouquet.setFlowerCount(10);
        bouquet.setAccessoryType(AccessoryType.RIBBON);
        return bouquet;
    }
}
