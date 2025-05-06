package com.sale_clothes.nhom11.dto.response;

import com.sale_clothes.nhom11.dto.AddressDTO;
import com.sale_clothes.nhom11.dto.OrderDetailDTO;
import com.sale_clothes.nhom11.dto.OrderStatusHistoryDTO;
import com.sale_clothes.nhom11.entity.Address;
import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.entity.OrderDetail;
import com.sale_clothes.nhom11.entity.OrderStatusHistory;
import com.sale_clothes.nhom11.enums.DeliveryMethod;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.enums.PaymentMethod;
import com.sale_clothes.nhom11.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrderResponse {
    private String orderId;
    private LocalDateTime orderDate;

    private OrderStatus status;
    private PaymentMethod paymentMethod;


    private Double totalAmount;


    private AddressDTO address;
    private String orderCode;


    private List<OrderDetailDTO> orderDetails;
    private List<OrderStatusHistoryDTO> orderStatusHistories;
}
