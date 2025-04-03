package com.sale_clothes.nhom11.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "address")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phoneNumber;
    private String province;
    private String district;
    private String village;
    private String detailAddress;
    private boolean typeAddress;
    @ManyToOne
    @JoinColumn(name = "kh_username", nullable = false)
    private KhachHang khachHang;
}
