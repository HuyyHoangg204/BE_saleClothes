package com.sale_clothes.nhom11.entity;

import com.sale_clothes.nhom11.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_status_history")
@Data
public class OrderStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private DonDatHang order;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime statusDate;
    private String description; // Ví dụ: "Đã giao cho đơn vị vận chuyển"
}
