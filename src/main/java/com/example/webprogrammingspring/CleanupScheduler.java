package com.example.webprogrammingspring;

import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.repository.OrderRepository;
import com.example.webprogrammingspring.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupScheduler {

    private final OrderRepository orderRepository;

    @Scheduled(cron = "0 */3 * * * ?")
    public void cleanupOrders() {

        List<Order> draftOrders = orderRepository.findAll().stream()
                .filter(order -> order.getStatus().equals(OrderStatus.DRAFT))
                .toList();
        orderRepository.deleteAll(draftOrders);
    }
}
