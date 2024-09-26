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
    private String khUserName;

    @Column(name = "kh_password",length = 255)

    private String khPassWord;

    @Column(name = "kh_ten")
    private String khTen;

    @Column(name = "kh_gioitinh", nullable = true)
    private Boolean khGioiTinh;

    @Column(name = "kh_diachi", nullable = true)
    private String khDiaChi;

    @Column(name = "kh_dienthoai", nullable = true)
    private String khDienThoai;

    @Column(name = "kh_email")
    private String khEmail;

    @Column(name = "kh_ngaysinh", nullable = true)
    private int khNgaySinh;

    @Column(name = "kh_thangsinh", nullable = true)
    private int khThangSinh;

    @Column(name = "kh_namsinh", nullable = true)
    private int khNamSinh;

    @Column(name = "kh_cmnd", nullable = true)
    private String khCmnd;
}
