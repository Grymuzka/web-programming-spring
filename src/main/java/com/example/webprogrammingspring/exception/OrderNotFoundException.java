package com.example.webprogrammingspring.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super("Order not found with id: " + orderId);
    }
}
