package com.sale_clothes.nhom11.controller;

import java.util.List;
import java.util.Map;

import com.sale_clothes.nhom11.dto.response.ProductCartResponseDTO;
import com.sale_clothes.nhom11.dto.response.ProductDetailResponseDTO;
import com.sale_clothes.nhom11.dto.response.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.impl.SanPhamServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

@RequestMapping("/api/v1")

public class SanPhamController {
    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @PostMapping("/add-sanpham")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<SanPhamDTO> createSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
        SanPhamDTO saveSanPhamDTO = sanPhamService.createSanPhamDTO(sanPhamDTO);
        return ApiResponse.<SanPhamDTO>builder().result(saveSanPhamDTO).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/sanphams")
    public ApiResponse<List<SanPhamDTO>> getAllSanPham() {
        List<SanPhamDTO> sanPhamDTOS = sanPhamService.getAllSanPhamDTOs();
        return ApiResponse.<List<SanPhamDTO>>builder().result(sanPhamDTOS).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/sanpham/{spMa}")
    public ApiResponse<?> updateSanPham(@PathVariable int spMa, @RequestBody SanPhamDTO sanPhamDTO) {
        try {
            sanPhamService.updateSanPhamDTO(spMa, sanPhamDTO);
            return ApiResponse.builder()
                    .message("Update san pham success")
                    .result(sanPhamDTO)
                    .build();
        } catch (Exception ex) {
            return ApiResponse.builder()
                    .message("Update san pham failed. Error: " + ex)
                    .build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-sanpham/{spMa}")
    public ApiResponse<String> deleteSanPham(@PathVariable int spMa) {
        sanPhamService.deleteSanPhamDTOById(spMa);
        return ApiResponse.<String>builder().result("delete success!!").build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/showSanPham")
    public ApiResponse<List<Map<String, Object>>> getAllSanPhamToShow() {
        List<Map<String, Object>> listProduct = sanPhamService.getSanPhamToShowManager();
        return ApiResponse.<List<Map<String, Object>>>builder().result(listProduct).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/sanpham/{id}")
    public ApiResponse<SanPhamDTO> getProductById(@PathVariable Integer id) {
        SanPhamDTO sanPhamDTO = sanPhamService.findSanPhamDTOById(id);
        return ApiResponse.<SanPhamDTO>builder().result(sanPhamDTO).build();
    }

    @GetMapping("/newProduct")
    public ApiResponse<List<ProductResponseDTO>> getNewProduct() {
        List<ProductResponseDTO> productResponseDTOList = sanPhamService.getProductDetails();
        return ApiResponse.<List<ProductResponseDTO>>builder()
                .result(productResponseDTOList)
                .build();
    }
    @GetMapping("/bestSellerProduct")
    public ApiResponse<List<ProductResponseDTO>> getBestSellerProduct() {
        List<ProductResponseDTO> productResponseDTOList = sanPhamService.getProductBestSeller();
        return ApiResponse.<List<ProductResponseDTO>>builder()
                .result(productResponseDTOList)
                .build();
    }
    @GetMapping("/flashSaleProduct")
    public ApiResponse<List<ProductResponseDTO>> getFlashSaleProduct() {
        List<ProductResponseDTO> productResponseDTOList = sanPhamService.getProductBestSeller();
        return ApiResponse.<List<ProductResponseDTO>>builder()
                .result(productResponseDTOList)
                .build();
    }
    @GetMapping("/recommendProduct")
    public ApiResponse<List<ProductResponseDTO>> getRecommendProduct() {
        List<ProductResponseDTO> productResponseDTOList = sanPhamService.getProductBestSeller();
        return ApiResponse.<List<ProductResponseDTO>>builder()
                .result(productResponseDTOList)
                .build();
    }

    @GetMapping("/detailProduct/{id}")
    public ApiResponse<ProductDetailResponseDTO> getProductDetail(@PathVariable int id) {
        ProductDetailResponseDTO productDetailResponseDTO = sanPhamService.getProductDetail(id);
        return ApiResponse.<ProductDetailResponseDTO>builder()
                .result(productDetailResponseDTO)
                .build();
    }

    @GetMapping("/productsByIds")
    public ApiResponse<List<ProductResponseDTO>> getListProductByIds(@RequestParam List<Integer> ids) {
        List<ProductResponseDTO> productResponseDTO = sanPhamService.getProductListByListId(ids);
        return ApiResponse.<List<ProductResponseDTO>>builder()
                .result(productResponseDTO)
                .build();
    }

    @GetMapping("/productCart/{id}")
    public ApiResponse<ProductCartResponseDTO> getProductCart(@PathVariable int id, @RequestParam int idColor) {
        ProductCartResponseDTO productCartResponseDTO = sanPhamService.getInfoProductCart(id, idColor);
        return ApiResponse.<ProductCartResponseDTO>builder()
                .result(productCartResponseDTO)
                .build();
    }
}
