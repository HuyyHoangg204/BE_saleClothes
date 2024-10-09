package com.sale_clothes.nhom11.dto;

import java.time.LocalDate;

import com.sale_clothes.nhom11.entity.*;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SanPhamDTO {
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





    }
//}
