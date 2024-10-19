package com.sale_clothes.nhom11.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @Column(name = "sp_ma")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spMa;

    @Column(name = "sp_ten")
    private String spTen;

    @Column(name = "sp_gia")
    private double spGia;

    @Column(name = "sp_giacu")
    private double spGiaCu;

    @Column(name = "sp_mota_ngan")
    private String spMoTaNgan;

    @Column(name = "sp_mota_chitiet")
    private String spMoTaChiTiet;

    @Column(name = "sp_ngaycapnhat")
    private LocalDate spNgayCapNhat;

    @Column(name = "sp_soluong")
    private int spSoLuong;

    @Column(name = "sp_color")
    private String spColor;

    @Column(name = "sp_Image")
    private String imageMain;

    @ManyToOne
    @JoinColumn(name = "dmc_ma")
    private DanhMucCon dmcMa;

    @ManyToOne
    @JoinColumn(name = "km_ma")
    private KhuyenMai khMa;

    @ManyToOne
    @JoinColumn(name = "lsp_ma")
    private LoaiSanPham lspMa;

    @ManyToOne
    @JoinColumn(name = "ch_ma")
    private CuaHang chMa;

    public SanPham(
            String spMa,
            String spTen,
            double spGia,
            double spGiaCu,
            String spMoTaNgan,
            String spMoTaChiTiet,
            LocalDate spNgayCapNhat,
            int spSoLuong,
            String imageMain,
            String spColor) {}

    //    public SanPham(
    //            String spMa,
    //            String spTen,
    //            double spGia,
    //            double spGiaCu,
    //            String spMoTaNgan,
    //            String spMoTaChiTiet,
    //            LocalDate spNgayCapNhat,
    //            int spSoLuong,
    //            String spImage,
    //            String spColor
    //            ) {
    //        this.sp_ma = sp_ma;
    //        this.sp_ten = sp_ten;
    //        this.sp_gia = sp_gia;
    //        this.sp_giaCu = sp_giaCu;
    //        this.sp_moTa_ngan = sp_moTa_ngan;
    //        this.sp_moTa_chiTiet = sp_moTa_chiTiet;
    //        this.sp_ngayCapNhat = sp_ngayCapNhat;
    //        this.sp_soLuong = sp_soLuong;
    //        this.sp_image = sp_image;
    //    }
}
