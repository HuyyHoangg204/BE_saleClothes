package com.sale_clothes.nhom11.dto.response;

import java.util.List;

import com.sale_clothes.nhom11.dto.VariantDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponseDTO {
    private Integer product_id;
    private String name;
    private double base_price;
    private Double oldPrice;
    private String description;
    private String gender;
    private int discount_percentage;
    private String product_code;
    private String material;
    private String instruction;
    private List<VariantDTO> variants;
}
