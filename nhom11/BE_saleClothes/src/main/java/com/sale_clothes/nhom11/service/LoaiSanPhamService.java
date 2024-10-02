package com.sale_clothes.nhom11.service;

import java.util.List;

import com.sale_clothes.nhom11.dto.LoaiSanPhamDTO;

public interface LoaiSanPhamService {
    LoaiSanPhamDTO createloaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO);

    List<LoaiSanPhamDTO> getAllLoaiSanPham();

    LoaiSanPhamDTO getLoaiSanPhamById(String Id);

    LoaiSanPhamDTO updateLoaiSanPham(String Id, LoaiSanPhamDTO loaiSanPhamDTO);

    void deleteLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO);
}
