package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.GioHangDTO;
import com.sale_clothes.nhom11.entity.GioHang;

public class GioHangMapper {
    public static GioHangDTO mapToGioHangDTO(GioHang gioHang) {
        return new GioHangDTO(
                gioHang.getGh_ma(),
                gioHang.getKh_userName()
        );
    }

    public static GioHang mapToGioHang(GioHangDTO gioHangDTO) {
        return  new GioHang(
                gioHangDTO.getGh_ma(),
                gioHangDTO.getKh_userName()
        );
    }
}
