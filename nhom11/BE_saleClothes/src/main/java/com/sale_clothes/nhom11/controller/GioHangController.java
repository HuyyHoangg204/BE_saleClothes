package com.sale_clothes.nhom11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.GioHangDTO;
import com.sale_clothes.nhom11.service.impl.GioHangServiceImpl;

@RestController
@RequestMapping("/api")
public class GioHangController {
    @Autowired
    private GioHangServiceImpl gioHangServiceImpl;

    @PostMapping("/giohangs")
    public ResponseEntity<GioHangDTO> createGioHangDTO(@RequestBody GioHangDTO gioHangDTO) {
        GioHangDTO saveGioHang = gioHangServiceImpl.createGioHang(gioHangDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveGioHang);
    }
}
