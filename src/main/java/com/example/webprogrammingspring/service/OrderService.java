package com.example.webprogrammingspring.service;

import com.example.webprogrammingspring.entity.Bouquet;
import com.example.webprogrammingspring.entity.Customer;
import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.exception.OrderNotFoundException;
import com.example.webprogrammingspring.repository.OrderRepository;
import com.example.webprogrammingspring.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BouquetService bouquetService;
    private final CustomerService customerService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        for (Bouquet bouquet : order.getBouquets()) {
            bouquetService.setBouquetDetails(bouquet, order);
        }

        return createDraftOrder(order);
    }

    public Order createDraftOrder(Order order) {
        order.setStatus(OrderStatus.DRAFT);
        return orderRepository.save(order);
    }

    public Order processDraftOrder(Long orderId, Customer customer) {
        Order order = findOrderById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        Customer existingCustomer = customerService.findCustomerByEmail(customer.getEmail());
        if (existingCustomer == null) {
            existingCustomer = customerService.createCustomer(customer);
        }

        order.setCustomer(existingCustomer);
        order.setStatus(OrderStatus.NEW);
        order.setTotalPrice(calculateTotalPrice(order));
        updateOrder(order);
        return order;
    }

    public double calculateTotalPrice(Order order) {
        return order.getBouquets().stream()
                .mapToDouble(Bouquet::getPrice)
                .sum();
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public Order changeOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        switch (order.getStatus()) {
            case NEW:
                order.setStatus(OrderStatus.CONFIRMED);
                break;
            case CONFIRMED:
                order.setStatus(OrderStatus.READY);
                break;
            default:
                throw new IllegalStateException("Cannot change status for this order");
        }

        return orderRepository.save(order);
    }
}