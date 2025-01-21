package com.example.webprogrammingspring.controller;

import com.example.webprogrammingspring.exception.OrderNotFoundException;
import com.example.webprogrammingspring.service.CustomerService;
import com.example.webprogrammingspring.service.OrderService;
import com.example.webprogrammingspring.entity.Customer;
import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping("/form")
    public String showOrderForm(@RequestParam Long orderId, Model model) {
        Order order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);

        if (!model.containsAttribute("customer")) {
            model.addAttribute("customer", new Customer());
        }

        return "orderForm";
    }

    @PostMapping("/form")
    public String processOrder(@RequestParam Long orderId,
                               @ModelAttribute Customer customer,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Wystąpił błąd w formularzu.");
            return "redirect:/order/form?orderId=" + orderId;
        }

        // Znalezienie zamówienia
        Order order = orderService.findOrderById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        // Znalezienie lub utworzenie klienta
        Customer existingCustomer = customerService.findCustomerByEmail(customer.getEmail());
        if (existingCustomer == null) {
            existingCustomer = customerService.createCustomer(customer);
        }

        // Powiązanie zamówienia z klientem i zmiana statusu
        order.setCustomer(existingCustomer);
        order.setStatus(OrderStatus.NEW);
        orderService.updateOrder(order);

        redirectAttributes.addFlashAttribute("order", order);
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String showConfirmationPage(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute("order", order);
        return "orderConfirmation";
    }
}