package com.example.shop.web.dto;

import com.example.shop.model.entity.Product;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Product product;
    private Integer quantity;
    private BigDecimal subtotal;
}
