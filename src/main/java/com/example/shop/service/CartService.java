package com.example.shop.service;

import com.example.shop.model.entity.Product;
import com.example.shop.web.dto.CartItemDto;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    void addProduct(Long productId, int qty);
    List<CartItemDto> getCartItems();
    void clearCart();
}
