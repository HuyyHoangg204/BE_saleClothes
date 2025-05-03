package com.sale_clothes.nhom11.enums;

public enum OrderStatus {
    PENDING,        // Mới đặt, chưa xử lý
    PROCESSING,     // Đang xử lý
    SHIPPED,        // Đã gửi hàng
    DELIVERED,      // Đã giao hàng
    CANCELLED,      // Đã hủy
    RETURNED        // Trả hàng
}
