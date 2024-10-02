package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.KhuyenMaiDTO;
import com.sale_clothes.nhom11.entity.KhuyenMai;

public class KhuyenMaiMapper {
    public static KhuyenMaiDTO mapToKhuyenMaiDTO(KhuyenMai khuyenMai) {
        return new KhuyenMaiDTO(
                khuyenMai.getKh_ma(),
                khuyenMai.getKm_ten(),
                khuyenMai.getKm_noiDung(),
                khuyenMai.getKm_tuNgay(),
                khuyenMai.getKm_denNgay());
    }

    public static KhuyenMai mapToKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        return new KhuyenMai(
                khuyenMaiDTO.getKh_ma(),
                khuyenMaiDTO.getKm_ten(),
                khuyenMaiDTO.getKm_noiDung(),
                khuyenMaiDTO.getKm_tuNgay(),
                khuyenMaiDTO.getKm_denNgay());
    }
}
