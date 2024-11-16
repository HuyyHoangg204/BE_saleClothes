package com.sale_clothes.nhom11.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.impl.KhachHangServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class KhachHangController {
    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;

    @PostMapping("/add-khachhang")
    public ResponseEntity<ApiResponse<KhachHangDTO>> createKhachHang(@RequestBody @Valid KhachHangDTO khachHangDTO, BindingResult result) {
        log.info("Controller : create user!!");

        // Kiểm tra nếu có lỗi validation
        if (result.hasErrors()) {
            // Lấy thông báo lỗi từ BindingResult
            String errorMessage = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", ")); // Kết hợp các thông báo lỗi nếu có nhiều lỗi
            // Trả về lỗi 400 với mã lỗi và thông báo chi tiết
            ApiResponse<KhachHangDTO> response = ApiResponse.<KhachHangDTO>builder()
                    .code(1003) // Mã lỗi
                    .message(errorMessage) // Thông báo lỗi từ result
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Trả về mã 400
        }

        // Nếu không có lỗi validation, thực hiện lưu dữ liệu
        KhachHangDTO savedKhachHang = khachHangServiceImpl.createKhachHang(khachHangDTO);

        ApiResponse<KhachHangDTO> response = ApiResponse.<KhachHangDTO>builder()
                .message("Đăng ký thành công")  // Thông báo thành công
                .result(savedKhachHang)  // Trả về đối tượng đã lưu
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK); // Trả về mã 200
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
