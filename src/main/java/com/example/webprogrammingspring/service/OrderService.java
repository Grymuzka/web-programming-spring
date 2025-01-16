package com.example.webprogrammingspring.service;

import com.example.webprogrammingspring.exception.OrderNotFoundException;
import com.example.webprogrammingspring.entity.Bouquet;
import com.example.webprogrammingspring.entity.CustomerOrder;
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
        CustomerOrder customerOrder = new CustomerOrder();
//        order.setCustomer(customer);
//        customerOrder.setBouquet(bouquet);
        customerOrder.setStatus(OrderStatus.NEW);
        orderRepository.save(customerOrder);
    }

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public CustomerOrder createDraftOrder(Bouquet bouquet) {
        CustomerOrder order = new CustomerOrder();
        order.setStatus(OrderStatus.DRAFT);
        order.setBouquet(bouquet);
        return orderRepository.save(order);
    }

    public CustomerOrder findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public void updateOrder(CustomerOrder order) {
        orderRepository.save(order);
    }
}