package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.LoaiSanPhamDTO;

import java.util.List;

public interface LoaiSanPhamService {
    LoaiSanPhamDTO createloaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO);
    List<LoaiSanPhamDTO> getAllLoaiSanPham();
    LoaiSanPhamDTO getLoaiSanPhamById(String Id);
    LoaiSanPhamDTO updateLoaiSanPham(String Id, LoaiSanPhamDTO loaiSanPhamDTO);
    void deleteLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO);
}
