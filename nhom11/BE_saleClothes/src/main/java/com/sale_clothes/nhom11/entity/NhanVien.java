package com.sale_clothes.nhom11.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
public class NhanVien {
    @Id
    @Column(name = "nv_ma")
    private String nv_ma;

    @Column(name = "nv_ten")
    private String nv_ten;

    @Column(name = "nv_diachi")
    private String nv_diaChi;

    @Column(name = "nv_email")
    private String nv_email;

    @Column(name = "nv_username")
    private String nv_userName;

    @Column(name = "nv_password")
    private String nv_passWord;

    @Column(name = "nv_ngayvao")
    private String nv_ngayVao;

    @Column(name = "nv_luong")
    private String nv_luong;

    @Column(name = "nv_vaitro")
    private String nv_vaitro;

    @ManyToOne
    @JoinColumn(name = "ch_ma")
    private CuaHang ch_ma;
}
