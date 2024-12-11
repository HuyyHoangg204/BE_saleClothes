package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.entity.ProductVariant;
import com.sale_clothes.nhom11.service.ProductVariantService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductVariantController {
    @Autowired
    private ProductVariantService productVariantService;

    @PostMapping("/add_product_variant")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<ProductVariantDTO> createProductVariant(@RequestBody ProductVariantDTO productVariantDTO) {
        ProductVariantDTO productVariantDTO1 = productVariantService.create(productVariantDTO);
        return ApiResponse.<ProductVariantDTO>builder()
                .result(productVariantDTO1)
                .build();
    }
}
