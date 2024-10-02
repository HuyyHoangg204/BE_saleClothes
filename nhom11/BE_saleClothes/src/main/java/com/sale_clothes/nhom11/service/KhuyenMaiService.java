package com.sale_clothes.nhom11.service;

import java.util.List;

import com.sale_clothes.nhom11.dto.KhuyenMaiDTO;

public interface KhuyenMaiService {

    KhuyenMaiDTO createKhuyenMai(KhuyenMaiDTO khuyenMaiDTO);

    List<KhuyenMaiDTO> getAllKhuyenMai();

    KhuyenMaiDTO getKhuyenMaiById(String id);

    KhuyenMaiDTO updateKhuyenMai(String id, KhuyenMaiDTO khuyenMaiDTO);

    void deleteKhuyenMai(KhuyenMaiDTO khuyenMaiDTO);
}
