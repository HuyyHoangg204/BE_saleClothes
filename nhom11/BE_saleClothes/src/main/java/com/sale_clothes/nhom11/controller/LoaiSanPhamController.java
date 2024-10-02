package com.sale_clothes.nhom11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.LoaiSanPhamDTO;
import com.sale_clothes.nhom11.service.impl.LoaiSanPhamServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoaiSanPhamController {
    @Autowired
    private LoaiSanPhamServiceImpl loaiSanPhamServiceImpl;

    @PostMapping("/loaisanphams")
    public ResponseEntity<LoaiSanPhamDTO> createLoaiSanPham(@RequestBody LoaiSanPhamDTO loaiSanPhamDTO) {
        LoaiSanPhamDTO saveLoaiSanPham = loaiSanPhamServiceImpl.createloaiSanPham(loaiSanPhamDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveLoaiSanPham);
    }
}
