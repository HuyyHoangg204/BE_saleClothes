package com.sale_clothes.nhom11.service;

import java.util.List;

import com.sale_clothes.nhom11.dto.KhachHangDTO;

public interface KhachHangService {
    KhachHangDTO createKhachHang(KhachHangDTO khachHangDTO);

    List<KhachHangDTO> getAllKhachHang();

    KhachHangDTO updateKhachHang(String id, KhachHangDTO khachHangDTO);

    KhachHangDTO getKhachHangById(String id);

    KhachHangDTO getMyInfo();

    void deleteKhachHang(String id);
}
