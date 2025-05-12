package com.sale_clothes.nhom11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEditOrderDTO {
    private Integer product_id;
    private String name;
    private double base_price;
    private String colorName;
    private String size;
    private int quantity;
    private double total;

}
