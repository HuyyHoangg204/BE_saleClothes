package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.DonDatHangDTO;
import com.sale_clothes.nhom11.dto.request.OrderEditRequest;
import com.sale_clothes.nhom11.dto.response.*;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.service.impl.DonDatHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<OrderManagerResponse>> getAllOrder(@RequestParam(defaultValue = "1") int page) {
        List<OrderManagerResponse> orderManagerResponses = donDatHangService.getAllOrderForManager(page - 1);
        return ApiResponse.<List<OrderManagerResponse>>builder()
                .result(orderManagerResponses)
                .build();
    }

    @GetMapping("/order-edit/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<OrderEditResponse> getOrderForEdit(@PathVariable String orderId) {
        OrderEditResponse orderEditResponse = donDatHangService.getOrderForEdit(orderId);
        return ApiResponse.<OrderEditResponse>builder()
                .result(orderEditResponse)
                .build();
    }

    @PutMapping("/order-status/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> updateOrderStatus(@PathVariable String orderId,@RequestBody OrderEditRequest orderEditRequest) {
        String message =  donDatHangService.editStatusOrder(orderId,orderEditRequest);
        return ApiResponse.<String>builder()
                .message(message)
                .build();
    }

    @GetMapping("/order-detail/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<OrderDetailResponse> getOrderForDetail(@PathVariable String orderId) {
        OrderDetailResponse orderDetailResponse = donDatHangService.getAllInformationOrder(orderId);
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailResponse)
                .build();
    }
}
