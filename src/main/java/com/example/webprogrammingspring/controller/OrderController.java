package com.example.webprogrammingspring.controller;

import com.example.webprogrammingspring.entity.Customer;
import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.service.OrderService;
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

    @GetMapping("/form")
    public String showOrderForm(@RequestParam Long orderId, Model model) {
        Order order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("bouquetCount", order.getBouquets().size());

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

        Order order = orderService.processDraftOrder(orderId, customer);

        redirectAttributes.addFlashAttribute("order", order);
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String showConfirmationPage(
            @ModelAttribute("order") Order order,
            Model model) {

        // Dodatkowe zabezpieczenie
        if (order == null) {
            return "redirect:/error";
        }

        model.addAttribute("order", order);
        model.addAttribute("bouquets", order.getBouquets());
        model.addAttribute("totalPrice", order.getTotalPrice());

        return "orderConfirmation";
    }
}