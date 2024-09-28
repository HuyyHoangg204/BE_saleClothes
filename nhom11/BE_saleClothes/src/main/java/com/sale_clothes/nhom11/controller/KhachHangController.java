package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.impl.KhachHangServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

        return ApiResponse.<KhachHangDTO>builder()
                .result(savedKhachHang)
                .build();
    }

    @GetMapping("/khachhangs")
    public ApiResponse<List<KhachHangDTO>> getAllKhachHang() {

        //Lấy thông tin xác thực người dùng hiện tại thông qua SecurityContextHolder.

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        //Ghi lại username (tên đăng nhập) của người dùng vào log.
        log.info("Username: {}", authentication.getName());

        //Lấy danh sách các quyền (roles/authorities) mà người dùng sở hữu.
        //Duyệt qua danh sách quyền và ghi lại từng quyền của người dùng vào log.
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<KhachHangDTO>>builder()
                .result(khachHangServiceImpl.getAllKhachHang())
                .build();
    }

    @GetMapping("/khachhangs/{userName}")
    public ApiResponse<KhachHangDTO> getKhachHang(@PathVariable("userName") String userName) {
        KhachHangDTO khachHangDTO = khachHangServiceImpl.getKhachHangById(userName);
        return ApiResponse.<KhachHangDTO>builder()
                .result(khachHangDTO)
                .build();
    }
}
