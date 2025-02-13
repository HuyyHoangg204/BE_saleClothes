package com.sale_clothes.nhom11.dto.response;

import java.util.List;

import com.sale_clothes.nhom11.dto.VariantDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private int productId;
    private String name;
    private Double basePrice;
    private Double oldPrice;
    private List<VariantDTO> variants;
}
