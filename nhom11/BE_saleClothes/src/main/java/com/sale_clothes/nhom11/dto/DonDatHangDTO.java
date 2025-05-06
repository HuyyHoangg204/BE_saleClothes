package com.sale_clothes.nhom11.dto;

import com.sale_clothes.nhom11.entity.Address;
import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.entity.OrderDetail;
import com.sale_clothes.nhom11.enums.DeliveryMethod;
import com.sale_clothes.nhom11.enums.PaymentMethod;
import com.sale_clothes.nhom11.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Data
public class DonDatHangDTO {
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private DeliveryMethod deliveryMethod;
    private Double shippingFee;

    private String username;
    private String orderCode;

    private Long addressId; // Information address, phone and fullname of user

    private List<OrderDetail> orderDetails; // Detail order contain quantity and product
}
