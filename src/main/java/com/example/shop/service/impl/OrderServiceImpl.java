package com.example.shop.service.impl;

import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.OrderItem;
import com.example.shop.model.entity.Product;
import com.example.shop.model.entity.User;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.OrderItemRepository;
import com.example.shop.service.CartService;
import com.example.shop.service.OrderService;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Order placeOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        List<OrderItem> items = cartService.getCartItems().stream().map(ci -> {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getProduct().getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())));
            return oi;
        }).collect(Collectors.toList());
        order.setItems(items);
        BigDecimal total = items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total);
        Order saved = orderRepository.save(order);
        // Items cascade and save
        cartService.clearCart();
        return saved;
    }

    @Override
    public List<Order> getOrdersForUser(User user) {
        return orderRepository.findByUser(user);
    }
}
