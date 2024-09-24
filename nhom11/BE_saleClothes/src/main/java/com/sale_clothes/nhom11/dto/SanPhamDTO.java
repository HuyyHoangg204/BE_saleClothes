package com.sale_clothes.nhom11.dto;

import com.sale_clothes.nhom11.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SanPhamDTO {
    private String sp_ma;
    private String sp_ten;
    private double sp_gia;
    private double sp_giaCu;
    private  String sp_moTa_ngan;
    private String sp_moTa_chiTiet;
    private LocalDate sp_ngayCapNhat;
    private int sp_soLuong;
    private String sp_image;
    private DanhMucCon dmc_ma;
    private KhuyenMai km_ma;
    private LoaiSanPhamDTO lsp_ma;
    private GioHang gh_ma;
    private CuaHang ch_ma;


    public SanPhamDTO(String sp_ma, String sp_ten, double sp_gia, double sp_giaCu, String sp_moTa_ngan, String sp_moTa_chiTiet, LocalDate sp_ngayCapNhat, int sp_soLuong, String sp_image) {
        this.sp_ma = sp_ma;
        this.sp_ten = sp_ten;
        this.sp_gia = sp_gia;
        this.sp_giaCu = sp_giaCu;
        this.sp_moTa_ngan = sp_moTa_ngan;
        this.sp_moTa_chiTiet = sp_moTa_chiTiet;
        this.sp_ngayCapNhat = sp_ngayCapNhat;
        this.sp_soLuong = sp_soLuong;
        this.sp_image = sp_image;
    }
}
