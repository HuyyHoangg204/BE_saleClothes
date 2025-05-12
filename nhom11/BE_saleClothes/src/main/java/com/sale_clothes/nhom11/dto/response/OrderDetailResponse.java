package com.sale_clothes.nhom11.dto.response;

import com.sale_clothes.nhom11.dto.AddressDTO;
import com.sale_clothes.nhom11.dto.OrderStatusHistoryDTO;
import com.sale_clothes.nhom11.dto.ProductEditOrderDTO;
import com.sale_clothes.nhom11.entity.OrderStatusHistory;
import com.sale_clothes.nhom11.enums.DeliveryMethod;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.enums.PaymentMethod;
import com.sale_clothes.nhom11.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
public class OrderDetailResponse {
    private String orderId;
    private LocalDateTime orderDate;

    private PaymentStatus paymentStatus;
    private DeliveryMethod deliveryMethod;
    private PaymentMethod paymentMethod;
    private Double shippingFee;

    private Double totalAmount;
    private String orderCode;

    private AddressDTO addressDTO;

    private List<OrderStatusHistoryDTO> orderStatusHistoryDTOS;


}
