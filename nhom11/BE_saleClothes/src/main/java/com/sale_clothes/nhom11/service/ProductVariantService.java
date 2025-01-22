package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductVariantService extends IService<ProductVariantDTO> {

    List<ProductVariantDTO> getAllProductVariantsByProductID(int id);
}
