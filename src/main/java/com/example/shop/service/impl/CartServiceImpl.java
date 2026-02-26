package com.example.shop.service.impl;

import com.example.shop.model.entity.Product;
import com.example.shop.service.CartService;
import com.example.shop.service.ProductService;
import com.example.shop.web.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final HttpSession session;
    private final ProductService productService;
    private static final String CART_KEY = "cart";

    @SuppressWarnings("unchecked")
    private Map<Long, Integer> getCart() {
        Object cartObj = session.getAttribute(CART_KEY);
        if (cartObj == null) {
            Map<Long, Integer> cart = new ConcurrentHashMap<>();
            session.setAttribute(CART_KEY, cart);
            return cart;
        }
        return (Map<Long, Integer>) cartObj;
    }

    @Override
    public void addProduct(Long productId, int qty) {
        Map<Long, Integer> cart = getCart();
        cart.merge(productId, qty, Integer::sum);
        session.setAttribute(CART_KEY, cart);
    }

    @Override
    public List<CartItemDto> getCartItems() {
        Map<Long, Integer> cart = getCart();
        List<CartItemDto> items = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Product product = productService.getProductById(entry.getKey());
            int quantity = entry.getValue();
            CartItemDto dto = new CartItemDto();
            dto.setProduct(product);
            dto.setQuantity(quantity);
            dto.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            items.add(dto);
        }
        return items;
    }

    @Override
    public void clearCart() {
        session.removeAttribute(CART_KEY);
    }
}
