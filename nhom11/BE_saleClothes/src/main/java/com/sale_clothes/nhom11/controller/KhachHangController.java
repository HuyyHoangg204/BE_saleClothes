package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.service.impl.KhachHangServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class KhachHangController {
    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;

    @PostMapping("/add-khachhang")
    public ResponseEntity<KhachHangDTO> createKhachHang(@RequestBody @Valid KhachHangDTO khachHangDTO) {
        KhachHangDTO savedKhachHang = khachHangServiceImpl.createKhachHang(khachHangDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedKhachHang);
    }

    @GetMapping("/khachhangs")
    public ResponseEntity<List<KhachHangDTO>> getAllKhachHang() {
        List<KhachHangDTO> listKhachHang = khachHangServiceImpl.getAllKhachHang();
        return ResponseEntity.ok(listKhachHang);
    }

    @GetMapping("/khachhangs/{userName}")
    public ResponseEntity<KhachHangDTO> getKhachHang(@PathVariable("userName") String userName) {
        KhachHangDTO khachHangDTO = khachHangServiceImpl.getKhachHangById(userName);
        return ResponseEntity.ok(khachHangDTO);
    }
}
