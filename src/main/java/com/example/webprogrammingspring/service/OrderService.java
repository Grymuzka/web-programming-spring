package com.example.webprogrammingspring.service;

import com.example.webprogrammingspring.exception.OrderNotFoundException;
import com.example.webprogrammingspring.entity.Bouquet;
import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.repository.OrderRepository;
import com.example.webprogrammingspring.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder() {
        Order order = new Order();
//        order.setCustomer(customer);
//        customerOrder.setBouquet(bouquet);
        order.setStatus(OrderStatus.NEW);
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createDraftOrder(Bouquet bouquet) {
        Order order = new Order();
        order.setStatus(OrderStatus.DRAFT);
        order.setBouquet(bouquet);
        return orderRepository.save(order);
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
}