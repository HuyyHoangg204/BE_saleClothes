package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.LoaiSanPhamDTO;
import com.sale_clothes.nhom11.entity.LoaiSanPham;

public class LoaiSanPhamMapper {

    public static LoaiSanPhamDTO mapToLoaiSanPhamDTO(LoaiSanPham loaiSanPham) {
        return new LoaiSanPhamDTO(
                loaiSanPham.getLsp_ma(),
                loaiSanPham.getLsp_ten(),
                loaiSanPham.getLsp_mota()
        );
    }

    public static LoaiSanPham mapToLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO) {
        return new LoaiSanPham(
                loaiSanPhamDTO.getLsp_ma(),
                loaiSanPhamDTO.getLsp_ten(),
                loaiSanPhamDTO.getLsp_mota()
        );
    }
}
