package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.SanPhamDTO;

import java.util.List;

public interface SanPhamService {
SanPhamDTO createSanPhamDTO(SanPhamDTO sanPhamDTO);
List<SanPhamDTO> getAllSanPhamDTOs();
SanPhamDTO findSanPhamDTOById(String id);

SanPhamDTO updateSanPhamDTO(String id, SanPhamDTO sanPhamDTO );

void deleteSanPhamDTO(SanPhamDTO sanPham);
}
