package com.example.shop.service;

import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.User;
import java.util.List;

public interface OrderService {
    Order placeOrder(User user);
    List<Order> getOrdersForUser(User user);
}
