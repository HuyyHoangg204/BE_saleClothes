package com.sale_clothes.nhom11.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cua_hang")
public class CuaHang {
    @Id
    @Column(name = "ch_ma")
    private String ch_ma;

    @Column(name = "ch_ten")
    private String ch_ten;

    @Column(name = "ch_diachi")
    private String ch_diaChi;

    @Column(name = "ch_sdt")
    private String ch_sdt;

    @Column(name = "email")
    private String email;
}
