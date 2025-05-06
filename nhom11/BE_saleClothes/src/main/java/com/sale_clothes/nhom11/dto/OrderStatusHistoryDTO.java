package com.sale_clothes.nhom11.dto;

import com.sale_clothes.nhom11.entity.DonDatHang;
import com.sale_clothes.nhom11.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderStatusHistoryDTO {
    private Long id;

    private String orderId;

    private OrderStatus status;

    private LocalDateTime statusDate;
    private String description; // Ví dụ: "Đã giao cho đơn vị vận chuyển"
}
