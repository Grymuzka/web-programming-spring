package com.example.webprogrammingspring.controller;

import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrderService orderService;

    @GetMapping("/panel")
    public String showAdminPanel(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "adminPanel";
    }

    @GetMapping("/panel/{orderId}")
    public String showOrderDetails(@PathVariable Long orderId, Model model) {
        Order order = orderService.findOrderById(orderId);

        model.addAttribute("order", order);
        model.addAttribute("bouquets", order.getBouquets());

        return "adminPanelOrderDetails";
    }

    @PostMapping("/panel/change-status/{orderId}")
    public String changeOrderStatus(@PathVariable Long orderId, RedirectAttributes redirectAttributes) {
        try {
            orderService.changeOrderStatus(orderId);
            redirectAttributes.addFlashAttribute("successMessage", "Status zamówienia został zmieniony");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nie można zmienić statusu zamówienia");
        }
        return "redirect:/admin/panel";
    }

}

