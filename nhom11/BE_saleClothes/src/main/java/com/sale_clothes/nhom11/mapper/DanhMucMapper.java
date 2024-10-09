package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.DanhMucDTO;
import com.sale_clothes.nhom11.entity.DanhMuc;

public class DanhMucMapper {
    public static DanhMucDTO mapToDanhMucDTO(DanhMuc danhMuc) {
        return new DanhMucDTO(danhMuc.getDmMa(), danhMuc.getDmTen(), danhMuc.getDmType());
    }

    public static DanhMuc mapToDanhMuc(DanhMucDTO danhMucDTO) {
        return new DanhMuc(danhMucDTO.getDmMa(), danhMucDTO.getDmTen(), danhMucDTO.getDmType());
    }
}
