package com.sale_clothes.nhom11.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.sale_clothes.nhom11.enums.DeliveryMethod;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.enums.PaymentMethod;
import com.sale_clothes.nhom11.enums.PaymentStatus;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.query.Order;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class DonDatHang {
    @Id
    @Column(name = "order_id", columnDefinition = "CHAR(36)")
    private String orderId;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    private Double shippingFee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username",referencedColumnName = "kh_username")
    private KhachHang khachHang;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address; // Information address, phone and fullname of user

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails; // Detail order contain quantity and product

    @PrePersist
    public void prePersist() {
        if (this.orderId == null) {
            this.orderId = UUID.randomUUID().toString(); // Đảm bảo UUID được sinh ra đúng cách
        }
    }

}
