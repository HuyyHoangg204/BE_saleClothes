package com.sale_clothes.nhom11.controller;


import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.dto.response.ProductResponseDTO;
import com.sale_clothes.nhom11.entity.FavoriteProduct;
import com.sale_clothes.nhom11.exception.AppException;
import com.sale_clothes.nhom11.service.impl.FavoriteProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite")
public class FavoriteProductController {
    @Autowired
    private FavoriteProductServiceImpl favoriteProductService;

    @PostMapping("/add")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public ApiResponse<String> addProductFavorite(@RequestParam("username") String username,
                                                  @RequestParam("productId") int productId){
      String message = favoriteProductService.addProductToFavoriteList(username,productId);
      return ApiResponse.<String>builder().message(message).build();
    }

    @GetMapping("/findAll/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public ApiResponse<List<ProductResponseDTO>> getAllFavoriteById(@PathVariable String username) {
        List<ProductResponseDTO> favoriteProducts = favoriteProductService.getFavoriteProducts(username);
        return ApiResponse.<List<ProductResponseDTO>>builder().result(favoriteProducts).build();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public ApiResponse<String> deleteFavoriteByUsername(@RequestParam String username,
                                                        @RequestParam int productId) {
        try {
            favoriteProductService.deleteByUsernameAndProductId(username, productId);
            return ApiResponse.<String>builder()
                    .message("Delete product favorites successfully")
                    .build();
        } catch (Exception e) {
            return ApiResponse.<String>builder()
                    .message("Delete product favorites failed: " + e.getMessage())
                    .build();
        }
    }
}
