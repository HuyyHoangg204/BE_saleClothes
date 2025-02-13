package com.sale_clothes.nhom11.service;

import java.util.List;
import java.util.Map;

import com.sale_clothes.nhom11.dto.SanPhamDTO;

public interface SanPhamService {
    SanPhamDTO createSanPhamDTO(SanPhamDTO sanPhamDTO);

    List<SanPhamDTO> getAllSanPhamDTOs();

    SanPhamDTO findSanPhamDTOById(Integer id);

    void updateSanPhamDTO(Integer id, SanPhamDTO sanPhamDTO);

    void deleteSanPhamDTOById(int id);

    List<Map<String, Object>> getSanPhamToShowManager();
}
