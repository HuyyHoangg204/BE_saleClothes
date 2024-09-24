package com.sale_clothes.nhom11.dto;

import com.sale_clothes.nhom11.entity.KhachHang;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GioHangDTO {
    private Integer gh_ma;
    private KhachHang kh_userName;
}
