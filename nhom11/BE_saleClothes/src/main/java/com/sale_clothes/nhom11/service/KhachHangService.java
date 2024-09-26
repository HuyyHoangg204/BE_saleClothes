package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.KhachHangDTO;

import java.util.List;

public interface KhachHangService {
    KhachHangDTO createKhachHang(KhachHangDTO khachHangDTO);
    List<KhachHangDTO> getAllKhachHang();
    KhachHangDTO updateKhachHang(String id,KhachHangDTO khachHangDTO);
    KhachHangDTO getKhachHangById(String id);
    void deleteKhachHang(String id);
}
