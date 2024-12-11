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
<<<<<<< HEAD
    private Integer spMa;

    private String spTen;

    private double spGia;

    private double spGiaCu;

    private String spMoTaNgan;

    private String spMoTaChiTiet;

    private LocalDate spNgayCapNhat;

    private int spSoLuong;

    private String imageMain;

    private String spColor;

    private Integer dmcMa;

    private KhuyenMai khMa;

    private LoaiSanPham lspMa;

    private CuaHang chMa;
=======
    private Integer product_id;
    private String name;
    private double base_price;
    private String description;
    private Integer dmcMaId;   // ID của DanhMucCon
//    private String kmMaId;    // ID của KhuyenMai
//    private String lspMaId;   // ID của LoaiSanPham
//    private String chMaId;    // ID của CuaHang
    private int discount_percentage;
    private String product_code;
    private String material;
    private String instruction;
>>>>>>> 1cd856ad (build: ProductVariant, Color entity)
}
// }
