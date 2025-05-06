package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.DonDatHangDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.dto.response.OrderResponse;
import com.sale_clothes.nhom11.service.impl.DonDatHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DonDatHangController {
    @Autowired
    private DonDatHangServiceImpl donDatHangService;

    @PostMapping("/order")
    @PreAuthorize("#dto.username == authentication.name or hasRole('ADMIN')")
    public ApiResponse<String> createOrder(@RequestBody DonDatHangDTO dto) {
        String message = donDatHangService.createOrder(dto);
        return ApiResponse.<String>builder()
                .message(message)
                .build();
    }

    @GetMapping("/order/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public ApiResponse<List<OrderResponse>> getAllOrderByUsername(@PathVariable String username) {
        List<OrderResponse> orderResponseList = donDatHangService.getAllOrderByUsername(username);
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderResponseList)
                .build();
    }
}
