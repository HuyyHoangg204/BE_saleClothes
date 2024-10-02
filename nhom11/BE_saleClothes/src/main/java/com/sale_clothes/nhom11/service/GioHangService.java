package com.sale_clothes.nhom11.service;

import java.util.List;

import com.sale_clothes.nhom11.dto.GioHangDTO;

public interface GioHangService {
    GioHangDTO createGioHang(GioHangDTO gioHangDTO);

    List<GioHangDTO> getAllGioHang();

    GioHangDTO getGioHangId(Integer id);

    GioHangDTO updateGioHang(Integer id, GioHangDTO gioHangDTO);

    void deleteGioHang(GioHangDTO gioHangDTO);
}
