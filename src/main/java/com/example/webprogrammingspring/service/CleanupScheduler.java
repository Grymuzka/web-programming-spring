package com.example.webprogrammingspring.service;

import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.repository.OrderRepository;
import com.example.webprogrammingspring.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CleanupScheduler {

    private final OrderRepository orderRepository;

    @Scheduled(cron = "0 */3 * * * ?")
    public void cleanupOrders() {
        log.info(">> Started order cleanup process");
        List<Order> draftOrders = orderRepository.findAll().stream()
                .filter(order -> order.getStatus().equals(OrderStatus.DRAFT))
                .toList();
        orderRepository.deleteAll(draftOrders);
        log.info("<< Finished order cleanup process - cleaned up {} orders", draftOrders.size());

    }
}
