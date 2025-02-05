package com.sale_clothes.nhom11.dto.response;

import com.sale_clothes.nhom11.dto.VariantDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDTO {
    private int productId;
    private String name;
    private Double basePrice;
    private Double oldPrice;
    private List<VariantDTO> variants;
}
