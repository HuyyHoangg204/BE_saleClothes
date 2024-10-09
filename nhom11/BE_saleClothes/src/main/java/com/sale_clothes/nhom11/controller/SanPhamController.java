package com.sale_clothes.nhom11.controller;

import java.util.List;

import com.sale_clothes.nhom11.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.service.impl.SanPhamServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("/*")
@RequestMapping("/api")
public class SanPhamController {
    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @PostMapping("/add-sanpham")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<SanPhamDTO> createSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
        SanPhamDTO saveSanPhamDTO = sanPhamService.createSanPhamDTO(sanPhamDTO);
        return ApiResponse.<SanPhamDTO>builder()
                .result(saveSanPhamDTO)
                .build();
    }

    @GetMapping("/sanphams")
    public ResponseEntity<List<SanPhamDTO>> getAllSanPhams() {
        List<SanPhamDTO> listSanPhams = sanPhamService.getAllSanPhamDTOs();
        return new ResponseEntity<>(listSanPhams, HttpStatus.OK);
    }
}
