package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/delete_product_variant/{id}")
    @PreAuthorize(("hasRole('ADMIN')"))
    public ApiResponse<String> deleteProductVariant(@PathVariable int id) {
        String message;
        try {
            productVariantService.delete(id);
            message = "Delete successfully with id: " + id;
        } catch (Exception e) {
            message = "Delete failed!! Error: " + e.getMessage();
        }
        return ApiResponse.<String>builder()
                .message(message)
                .build();
    }

    @GetMapping("/productVariantsByProductID/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<ProductVariantDTO>> getAllProductVariantsByProductID(@PathVariable int id) {
        List<ProductVariantDTO> productVariantDTOS = productVariantService.getAllProductVariantsByProductID(id);
        return ApiResponse.<List<ProductVariantDTO>>builder()
                .result(productVariantDTOS)
                .build();
    }


}
