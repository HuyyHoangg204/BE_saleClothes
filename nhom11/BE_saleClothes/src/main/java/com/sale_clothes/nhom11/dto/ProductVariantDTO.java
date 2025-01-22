package com.sale_clothes.nhom11.dto;

import com.sale_clothes.nhom11.entity.Color;
import com.sale_clothes.nhom11.entity.SanPham;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductVariantDTO {
    private Integer variant_id;
    private int  product_id;
    private int color_id;
    private Set<String> size;
    private int stockQuantity;
    private String colorName;
    private String colorCode;
}
