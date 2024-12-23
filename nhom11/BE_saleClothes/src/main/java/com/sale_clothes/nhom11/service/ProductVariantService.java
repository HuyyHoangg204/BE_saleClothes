package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;

import java.util.List;

public interface ProductVariantService extends IService<ProductVariantDTO> {
    List<ProductVariantDTO> getAllProductVariantsByProductID(int id);
}
