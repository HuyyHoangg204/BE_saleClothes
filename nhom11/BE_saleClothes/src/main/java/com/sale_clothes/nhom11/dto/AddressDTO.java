package com.sale_clothes.nhom11.dto;

import com.sale_clothes.nhom11.entity.KhachHang;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    private Long id;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 2, max = 50, message = "Tên phải từ 2 đến 50 ký tự!")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên chỉ được chứa chữ cái!")
    private String fullName;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0[1-9])[0-9]{8,9}$", message = "Số điện thoại không hợp lệ! (Phải bắt đầu bằng 0 và có 10-11 số)")
    private String phoneNumber;
    @NotBlank(message = "Tỉnh thành không được để trống")
    private String province;
    @NotBlank(message = "Huyện không được để trống")
    private String district;
    @NotBlank(message = "Phường xã không được để trống")
    private String village;
    private String detailAddress;
    @NotNull(message = "Loại địa chỉ không được để trống")
    private boolean typeAddress;
    @NotBlank(message = "Username không được để trống")
    @Size(min = 5, message = "Username không dưới 5 ký tụ")
    private String userName;
}
