package com.sale_clothes.nhom11.dto.response;

import com.sale_clothes.nhom11.dto.AddressDTO;
import com.sale_clothes.nhom11.dto.ProductEditOrderDTO;
import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.enums.DeliveryMethod;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderEditResponse {
    private String orderId;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private DeliveryMethod deliveryMethod;

    private Double totalAmount;
    private String orderCode;

    private AddressDTO addressDTO;

    private List<ProductEditOrderDTO> productEditOrderDTOS;
}
