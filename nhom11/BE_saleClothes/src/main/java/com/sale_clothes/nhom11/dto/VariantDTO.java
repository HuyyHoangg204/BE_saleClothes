package com.sale_clothes.nhom11.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
public class VariantDTO {
    private Integer variant_id;
    private int color_id;
    private Set<String> size;
    private String colorCode;
    private List<String> imageUrl;
}
