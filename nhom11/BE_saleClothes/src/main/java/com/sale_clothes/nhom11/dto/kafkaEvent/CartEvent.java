package com.sale_clothes.nhom11.dto.kafkaEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEvent {
    private String username;
    private int productId;
    private String size;
    private String colorId;
    private int quantity;
}
