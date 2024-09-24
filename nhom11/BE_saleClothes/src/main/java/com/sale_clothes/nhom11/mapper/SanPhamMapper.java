package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.entity.SanPham;

public class SanPhamMapper {
    public static SanPhamDTO mapToSanPhamDTO(SanPham sanPham) {
        return new SanPhamDTO(
                sanPham.getSp_ma(),
                sanPham.getSp_ten(),
                sanPham.getSp_gia(),
                sanPham.getSp_giaCu(),
                sanPham.getSp_moTa_ngan(),
                sanPham.getSp_moTa_chiTiet(),
                sanPham.getSp_ngayCapNhat(),
                sanPham.getSp_soLuong(),
                sanPham.getSp_image()

        );
    }

    public static SanPham mapToSanPham(SanPhamDTO sanPhamDTO) {
        return  new SanPham(
                sanPhamDTO.getSp_ma(),
                sanPhamDTO.getSp_ten(),
                sanPhamDTO.getSp_gia(),
                sanPhamDTO.getSp_giaCu(),
                sanPhamDTO.getSp_moTa_ngan(),
                sanPhamDTO.getSp_moTa_chiTiet(),
                sanPhamDTO.getSp_ngayCapNhat(),
                sanPhamDTO.getSp_soLuong(),
                sanPhamDTO.getSp_image()

        );
    }
}
