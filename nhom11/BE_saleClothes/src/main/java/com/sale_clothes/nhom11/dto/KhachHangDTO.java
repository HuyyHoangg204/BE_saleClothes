package com.sale_clothes.nhom11.dto;

import java.util.Set;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KhachHangDTO {
    @Size(min = 5, message = "Username không dưới 5 ký tụ")
    private String khUserName;

    @Size(min = 8, message = "Password không dưới 8 ký tự!")
    private String khPassWord;

    private String khTen;

    private Boolean khGioiTinh;

    private String khDiaChi;

    private String khDienThoai;

    private String khEmail;

    private int khNgaySinh;

    private int kh_thangSinh;

    private int kh_namSinh;

    private String kh_cmnd;
    private Set<String> roles;
}
