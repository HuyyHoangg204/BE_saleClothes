package com.sale_clothes.nhom11.service;

import java.util.List;

import com.sale_clothes.nhom11.dto.DanhMucDTO;

public interface DanhMucService {
    DanhMucDTO createDanhMucDTO(DanhMucDTO danhMucDTO);

    List<DanhMucDTO> findALLDanhMucDTOs();

    DanhMucDTO findDanhMucDTOById(Integer id);

    DanhMucDTO updateDanhMucDTO(Integer id, DanhMucDTO danhMucDTO);

    void delelteDanhMucDTOs(DanhMucDTO danhMucDTO);
}
