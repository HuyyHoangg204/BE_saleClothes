package com.sale_clothes.nhom11.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @Column(name = "kh_username")
    private String kh_userName;

    @Column(name = "kh_password")
    private String kh_passWord;

    @Column(name = "kh_ten")
    private String kh_ten;

    @Column(name = "kh_gioitinh")
    private Boolean kh_gioiTinh;

    @Column(name = "kh_diachi")
    private String kh_diaChi;

    @Column(name = "kh_dienthoai")
    private String kh_dienThoai;

    @Column(name = "kh_email")
    private String kh_email;

    @Column(name = "kh_ngaysinh")
    private int kh_ngaySinh;

    @Column(name = "kh_thangsinh")
    private int kh_thangSinh;

    @Column(name = "kh_namsinh")
    private int kh_namSinh;

    @Column(name = "kh_cmnd")
    private String kh_cmnd;
}
