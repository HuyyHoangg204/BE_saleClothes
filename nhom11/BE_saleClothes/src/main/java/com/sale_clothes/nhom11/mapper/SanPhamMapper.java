package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.entity.DanhMucCon;
import com.sale_clothes.nhom11.entity.SanPham;

public class SanPhamMapper {
    public static SanPhamDTO mapToSanPhamDTO(SanPham sanPham) {
        return SanPhamDTO.builder()
                .spMa(sanPham.getSpMa())
                .spTen(sanPham.getSpTen())
                .spGia(sanPham.getSpGia())
                .spGiaCu(sanPham.getSpGiaCu())
                .spMoTaChiTiet(sanPham.getSpMoTaChiTiet())
                .spColor(sanPham.getSpColor())
                .spMoTaNgan(sanPham.getSpMoTaNgan())
                .spNgayCapNhat(sanPham.getSpNgayCapNhat())
                .spSoLuong(sanPham.getSpSoLuong())
                .dmcMa(sanPham.getDmcMa().getDmcMa())
                .build();

    }

    public static SanPham mapToSanPham(SanPhamDTO sanPhamDTO) {
        DanhMucCon dmc = new DanhMucCon();
        dmc.setDmcMa(sanPhamDTO.getDmcMa());

        return SanPham.builder()
                .spMa(sanPhamDTO.getSpMa())
                .spColor(sanPhamDTO.getSpColor())
                .spGia(sanPhamDTO.getSpGia())
                .spGiaCu(sanPhamDTO.getSpGiaCu())
                .spTen(sanPhamDTO.getSpTen())
                .spMoTaChiTiet(sanPhamDTO.getSpMoTaChiTiet())
                .spMoTaNgan(sanPhamDTO.getSpMoTaNgan())
                .spNgayCapNhat(sanPhamDTO.getSpNgayCapNhat())
                .imageMain(sanPhamDTO.getImageMain())
                .dmcMa(dmc)
                .build();
}
}
