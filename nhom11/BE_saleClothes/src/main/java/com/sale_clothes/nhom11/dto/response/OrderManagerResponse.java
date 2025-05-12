package com.sale_clothes.nhom11.dto.response;

import com.sale_clothes.nhom11.dto.AddressDTO;
import com.sale_clothes.nhom11.dto.OrderDetailDTO;
import com.sale_clothes.nhom11.dto.OrderStatusHistoryDTO;
import com.sale_clothes.nhom11.enums.DeliveryMethod;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.enums.PaymentMethod;
import com.sale_clothes.nhom11.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder

public class OrderManagerResponse {
    private String orderId;
    private LocalDateTime orderDate;
    private String fullName;

    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private DeliveryMethod deliveryMethod;

    private Double totalAmount;
    private String orderCode;

}
