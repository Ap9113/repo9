package com.example.shop.web.controller;

import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.User;
import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal UserDetails userDetails) {
        // Fetch application User entity by username
        User user = new User();
        user.setUsername(userDetails.getUsername());
        // In a real app, load full User from DB
        Order order = orderService.placeOrder(user);
        return "redirect:/orders/" + order.getId();
    }

    @GetMapping("/orders")
    public String listOrders(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        model.addAttribute("orders", orderService.getOrdersForUser(user));
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        // Implementation omitted for brevity
        return "order_detail";
    }
}
