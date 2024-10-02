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
                danhMucCon.getDmc_ma(),
                danhMucCon.getDmc_ten(),
                danhMucCon.getDm_ma() != null
                        ? danhMucCon.getDm_ma().getDm_ma()
                        : null // Lấy mã danh mục từ đối tượng DanhMuc
                );
    }

    // Phương thức để chuyển đổi từ DanhMucConDTO sang DanhMucCon
    public static DanhMucCon mapToDanhMucCon(DanhMucConDTO danhMucConDTO) {
        if (danhMucConDTO == null) {
            return null; // Trả về null nếu danhMucConDTO là null
        }

        DanhMucCon danhMucCon = new DanhMucCon();
        danhMucCon.setDmc_ma(danhMucConDTO.getDmc_ma());
        danhMucCon.setDmc_ten(danhMucConDTO.getDmc_ten());

        // Tạo đối tượng DanhMuc nếu cần
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setDm_ma(danhMucConDTO.getDm_ma()); // Thiết lập mã danh mục
        danhMucCon.setDm_ma(danhMuc); // Thiết lập đối tượng DanhMuc vào danhMucCon

        return danhMucCon;
    }
}
