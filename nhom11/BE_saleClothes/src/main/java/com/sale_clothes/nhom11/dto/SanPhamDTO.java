package com.sale_clothes.nhom11.dto;

import java.time.LocalDate;

import com.sale_clothes.nhom11.entity.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SanPhamDTO {
    private Integer product_id;
    private String name;
    private double base_price;
    private String description;
    private boolean is_is_discounted;
    private Integer dmcMaId;   // ID của DanhMucCon
    private String kmMaId;    // ID của KhuyenMai
    private String lspMaId;   // ID của LoaiSanPham
    private String chMaId;    // ID của CuaHang
    private int discount_percentage;
    private String product_code;
    private String material;
    private String instruction;
}
