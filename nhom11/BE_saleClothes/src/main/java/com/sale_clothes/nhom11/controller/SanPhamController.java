package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.service.impl.SanPhamServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("/*")
@RequestMapping("/api")
public class SanPhamController {
    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @PostMapping("/sanphams")
    public ResponseEntity<SanPhamDTO> createSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
        SanPhamDTO saveSanPhamDTO = sanPhamService.createSanPhamDTO(sanPhamDTO);
        return ResponseEntity.status((HttpStatus.CREATED)).body(saveSanPhamDTO);
    }

    @GetMapping("/sanphams")
   public ResponseEntity<List<SanPhamDTO>> getAllSanPhams() {
        List<SanPhamDTO> listSanPhams = sanPhamService.getAllSanPhamDTOs();
        return new ResponseEntity<>(listSanPhams, HttpStatus.OK);
    }
}
