package com.sale_clothes.nhom11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.impl.SanPhamServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
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
}
