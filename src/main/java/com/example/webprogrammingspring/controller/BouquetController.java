package com.example.webprogrammingspring.controller;

import com.example.webprogrammingspring.entity.Order;
import com.example.webprogrammingspring.service.BouquetService;
import com.example.webprogrammingspring.entity.Bouquet;
import com.example.webprogrammingspring.service.OrderService;
import com.example.webprogrammingspring.type.AccessoryType;
import com.example.webprogrammingspring.type.FlowerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bouquet")
@RequiredArgsConstructor
public class BouquetController {

    private final BouquetService bouquetService;
    private final OrderService orderService;

    @GetMapping("/customize")
    public String showCustomizationForm(Model model) {
        if (!model.containsAttribute("bouquet")) {
            model.addAttribute("bouquet", new Bouquet());
        }
        model.addAttribute("flowerTypes", FlowerType.values());
        model.addAttribute("accessoryTypes", AccessoryType.values());

        return "customizeBouquet";
    }

    @PostMapping("/customize")
    public String processCustomization(
            @ModelAttribute Bouquet bouquet,
            RedirectAttributes redirectAttributes) {

        Bouquet savedBouquet = bouquetService.saveBouquet(bouquet);
        Order draftOrder = orderService.createDraftOrder(savedBouquet);

        redirectAttributes.addFlashAttribute("bouquet", savedBouquet);
        redirectAttributes.addFlashAttribute("order", draftOrder);
        return "redirect:/bouquet/summary";
    }

    @GetMapping("/summary")
    public String showBouquetSummary(@ModelAttribute("bouquet") Bouquet bouquet, @ModelAttribute("order") Order order, Model model) {
        model.addAttribute("bouquet", bouquet);
        model.addAttribute("orderId", order.getId());
        return "bouquetSummary";
    }

}
