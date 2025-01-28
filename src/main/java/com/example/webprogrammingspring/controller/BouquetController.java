package com.example.webprogrammingspring.controller;

import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.service.BouquetService;
import com.example.webprogrammingspring.service.OrderService;
import com.example.webprogrammingspring.type.AccessoryType;
import com.example.webprogrammingspring.type.FlowerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/bouquet")
@RequiredArgsConstructor
public class BouquetController {

    private final BouquetService bouquetService;
    private final OrderService orderService;

    @GetMapping("/customize")
    public String showCustomizationForm(Model model) {
        Order draftOrder = new Order();
        draftOrder.setBouquets(new ArrayList<>());

        model.addAttribute("order", draftOrder);
        model.addAttribute("flowerTypes", FlowerType.values());
        model.addAttribute("accessoryTypes", AccessoryType.values());
        return "customizeBouquet";
    }

    @PostMapping("/customize")
    public String processCustomization(
            @ModelAttribute("order") Order order,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "customizeBouquet";
        }

        Order savedOrder = orderService.saveOrder(order);

        redirectAttributes.addFlashAttribute("order", savedOrder);
        redirectAttributes.addFlashAttribute("bouquets", savedOrder.getBouquets());

        return "redirect:/bouquet/summary";
    }

    @GetMapping("/summary")
    public String showBouquetSummary(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute("bouquets", order.getBouquets());
        model.addAttribute("orderId", order.getId());
        return "bouquetSummary";
    }

}
