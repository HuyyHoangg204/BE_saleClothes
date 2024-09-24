package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.DanhMucDTO;
import com.sale_clothes.nhom11.entity.DanhMuc;

public class DanhMucMapper {
    public static DanhMucDTO mapToDanhMucDTO(DanhMuc danhMuc) {
        return new DanhMucDTO(
                danhMuc.getDm_ma(),
                danhMuc.getDm_ten()
        );
    }
    public static DanhMuc mapToDanhMuc(DanhMucDTO danhMucDTO) {
        return new DanhMuc(
                danhMucDTO.getDm_ma(),
                danhMucDTO.getDm_ten()
        );
    }
}
