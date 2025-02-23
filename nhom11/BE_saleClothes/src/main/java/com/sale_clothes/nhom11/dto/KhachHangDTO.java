package com.sale_clothes.nhom11.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Tên không được để trống!")
    @Size(min = 2, max = 50, message = "Tên phải từ 2 đến 50 ký tự!")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên chỉ được chứa chữ cái!")
    private String khTen;

    private Boolean khGioiTinh;

    private String khDiaChi;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Pattern(regexp = "^(0[1-9])[0-9]{8,9}$", message = "Số điện thoại không hợp lệ! (Phải bắt đầu bằng 0 và có 10-11 số)")
    private String khDienThoai;

    private String khEmail;

    private int khNgaySinh;

    private int kh_thangSinh;

    private int kh_namSinh;

    private String kh_cmnd;
    private Set<String> roles;
}
