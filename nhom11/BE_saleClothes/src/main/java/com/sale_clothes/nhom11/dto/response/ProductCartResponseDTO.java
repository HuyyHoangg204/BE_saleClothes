package com.sale_clothes.nhom11.dto.response;

import com.sale_clothes.nhom11.dto.VariantDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartResponseDTO {
    private Integer product_id;
    private String name;
    private double base_price;
    private String colorName;
    private VariantDTO variants;
}
