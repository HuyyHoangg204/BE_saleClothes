package com.sale_clothes.nhom11.service;

import java.util.List;

import com.sale_clothes.nhom11.dto.DanhMucConDTO;

public interface DanhMucConService {
    DanhMucConDTO createDanhMucCon(DanhMucConDTO danhMucConDTO);

    List<DanhMucConDTO> getAllDanhMucCon();

    DanhMucConDTO getDanhMucConById(Integer id);

    DanhMucConDTO updateDanhMucCon(Integer id, DanhMucConDTO danhMucConDTO);

    void deleteDanhMucCon(DanhMucConDTO danhMucConDTO);
}
