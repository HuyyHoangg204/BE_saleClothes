package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.DanhMucConDTO;
import com.sale_clothes.nhom11.dto.DanhMucDTO;

import java.util.List;

public interface DanhMucConService {
    DanhMucConDTO createDanhMucCon(DanhMucConDTO danhMucConDTO);
    List<DanhMucConDTO> getAllDanhMucCon();
    DanhMucConDTO getDanhMucConById(Integer id);
    DanhMucConDTO updateDanhMucCon(Integer id, DanhMucConDTO danhMucConDTO);
    void deleteDanhMucCon(DanhMucConDTO danhMucConDTO);
}
