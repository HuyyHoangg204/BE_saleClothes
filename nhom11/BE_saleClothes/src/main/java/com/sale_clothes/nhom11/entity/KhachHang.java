package com.sale_clothes.nhom11.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "khach_hang")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhachHang {
    @Id
    @Column(name = "kh_username")
    String khUserName;

    @Column(name = "kh_password",length = 255)

    String khPassWord;

    @Column(name = "kh_ten")
    String khTen;

    @Column(name = "kh_gioitinh", nullable = true)
    Boolean khGioiTinh;

    @Column(name = "kh_diachi", nullable = true)
    String khDiaChi;

    @Column(name = "kh_dienthoai", nullable = true)
    String khDienThoai;

    @Column(name = "kh_email")
    String khEmail;

    @Column(name = "kh_ngaysinh", nullable = true)
    int khNgaySinh;

    @Column(name = "kh_thangsinh", nullable = true)
    int khThangSinh;

    @Column(name = "kh_namsinh", nullable = true)
    int khNamSinh;

    @Column(name = "kh_cmnd", nullable = true)
    String khCmnd;

    @ElementCollection
    @CollectionTable(name = "khachhang_roles", joinColumns = @JoinColumn(name = "kh_username"))
    @Column(name = "role")
    Set<String> roles;
}
