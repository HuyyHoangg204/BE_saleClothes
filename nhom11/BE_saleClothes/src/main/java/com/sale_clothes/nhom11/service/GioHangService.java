package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.DanhMucConDTO;
import com.sale_clothes.nhom11.dto.GioHangDTO;

import java.util.List;

public interface GioHangService {
    GioHangDTO createGioHang(GioHangDTO gioHangDTO);
    List<GioHangDTO> getAllGioHang();
    GioHangDTO getGioHangId(Integer id);
    GioHangDTO updateGioHang(Integer id, GioHangDTO gioHangDTO);
    void deleteGioHang(GioHangDTO gioHangDTO);
}
