package com.sale_clothes.nhom11.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.impl.KhachHangServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Slf4j
public class KhachHangController {
    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;

    @PostMapping("/add-khachhang")
    public ApiResponse<KhachHangDTO> createKhachHang(@RequestBody @Valid KhachHangDTO khachHangDTO) {

        KhachHangDTO savedKhachHang = khachHangServiceImpl.createKhachHang(khachHangDTO);

        return ApiResponse.<KhachHangDTO>builder().result(savedKhachHang).build();
    }

    @PreAuthorize("hasRole('ADMIN')") // Kiểm tra trước mới thực hiện
    @GetMapping("/khachhangs")
    public ApiResponse<List<KhachHangDTO>> getAllKhachHang() {
        log.info("In method get Users");
        return ApiResponse.<List<KhachHangDTO>>builder()
                .result(khachHangServiceImpl.getAllKhachHang())
                .build();
    }

    @PostAuthorize("returnObject.result.khUserName.equals(authentication.name)") // Thực hiện rồi mới kiểm tra
    @GetMapping("/khachhang/{userName}")
    public ApiResponse<KhachHangDTO> getKhachHang(@PathVariable("userName") String userName) {
        KhachHangDTO khachHangDTO = khachHangServiceImpl.getKhachHangById(userName);
        return ApiResponse.<KhachHangDTO>builder().result(khachHangDTO).build();
    }

    @GetMapping("/info")
    public ApiResponse<KhachHangDTO> getInfo() {
        KhachHangDTO khachHangDTO = khachHangServiceImpl.getMyInfo();
        return ApiResponse.<KhachHangDTO>builder().result(khachHangDTO).build();
    }

}
