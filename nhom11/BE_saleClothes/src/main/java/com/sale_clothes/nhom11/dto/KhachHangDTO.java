package com.sale_clothes.nhom11.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KhachHangDTO {
    @Size(min = 5, message = "Username không dưới 5 ký tụ")
    private String khUserName;

    @Size(min = 8, message = "Password không dưới 8 ký tự!")
    private String khPassWord;

    private String khTen;

    private Boolean khGioiTinh;

    private String khDiaChi;

    private String khDienThoai;

    @Email(message = "Email không hợp lệ!")
    private String khEmail;

    private int khNgaySinh;

    private int kh_thangSinh;

    private int kh_namSinh;

    private String kh_cmnd;
    private Set<String> roles;
}
