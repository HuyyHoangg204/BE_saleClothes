package com.sale_clothes.nhom11.service;

import java.util.List;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;

public interface ProductVariantService extends IService<ProductVariantDTO,Integer> {

    List<ProductVariantDTO> getAllProductVariantsByProductID(int id);
}
