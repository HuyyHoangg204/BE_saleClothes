package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.entity.KhachHang;

public class KhachHangMapper {
    public static KhachHangDTO mapToKhachHangDTO(KhachHang khachHang) {
        return new KhachHangDTO(
                khachHang.getKhUserName(),
                khachHang.getKhPassWord(),
                khachHang.getKhTen(),
                khachHang.getKhGioiTinh(),
                khachHang.getKhDiaChi(),
                khachHang.getKhDienThoai(),
                khachHang.getKhEmail(),
                khachHang.getKhNgaySinh(),
                khachHang.getKhThangSinh(),
                khachHang.getKhNamSinh(),
                khachHang.getKhCmnd()
        );
    }

    public static KhachHang mapToKhachHang(KhachHangDTO khachHangDTO) {
        return new KhachHang(
                khachHangDTO.getKhUserName(),
                khachHangDTO.getKhPassWord(),
                khachHangDTO.getKhTen(),
                khachHangDTO.getKhGioiTinh(),
                khachHangDTO.getKhDiaChi(),
                khachHangDTO.getKhDienThoai(),
                khachHangDTO.getKhEmail(),
                khachHangDTO.getKhNgaySinh(),
                khachHangDTO.getKh_thangSinh(),
                khachHangDTO.getKh_namSinh(),
                khachHangDTO.getKh_cmnd()
        );
    }
}

