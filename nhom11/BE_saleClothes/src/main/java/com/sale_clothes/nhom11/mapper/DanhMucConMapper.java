package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.DanhMucConDTO;
import com.sale_clothes.nhom11.entity.DanhMuc;
import com.sale_clothes.nhom11.entity.DanhMucCon;

public class DanhMucConMapper {

    // Phương thức để chuyển đổi từ DanhMucCon sang DanhMucConDTO
    public static DanhMucConDTO mapToDanhMucConDTO(DanhMucCon danhMucCon) {
        if (danhMucCon == null) {
            return null; // Trả về null nếu danhMucCon là null
        }

        return new DanhMucConDTO(
                danhMucCon.getDmcMa(),
                danhMucCon.getDmcTen(),
                danhMucCon.getDmMa() != null
                        ? danhMucCon.getDmMa().getDmMa()
                        : null // Lấy mã danh mục từ đối tượng DanhMuc
        );
    }

    // Phương thức để chuyển đổi từ DanhMucConDTO sang DanhMucCon
    public static DanhMucCon mapToDanhMucCon(DanhMucConDTO danhMucConDTO) {
        if (danhMucConDTO == null) {
            return null; // Trả về null nếu danhMucConDTO là null
        }

        DanhMucCon danhMucCon = new DanhMucCon();
        danhMucCon.setDmcMa(danhMucConDTO.getDmcMa());
        danhMucCon.setDmcTen(danhMucConDTO.getDmcTen());

        // Tạo đối tượng DanhMuc nếu cần
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setDmMa(danhMucConDTO.getDmMa()); // Thiết lập mã danh mục
        danhMucCon.setDmMa(danhMuc); // Thiết lập đối tượng DanhMuc vào danhMucCon

        return danhMucCon;
    }
}
