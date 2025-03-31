package com.ecommerce.payment.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
}
