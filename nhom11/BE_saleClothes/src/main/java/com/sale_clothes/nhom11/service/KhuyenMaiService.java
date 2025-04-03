package com.sale_clothes.nhom11.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import com.sale_clothes.nhom11.dto.KhuyenMaiDTO;

public interface KhuyenMaiService {

    KhuyenMaiDTO createKhuyenMai(KhuyenMaiDTO khuyenMaiDTO);

    List<KhuyenMaiDTO> getAllKhuyenMai(Integer indexPage);

    Optional<KhuyenMaiDTO> getKhuyenMaiById(String id);

    KhuyenMaiDTO updateKhuyenMai(String id, KhuyenMaiDTO khuyenMaiDTO);

    void deleteKhuyenMai(KhuyenMaiDTO khuyenMaiDTO);
}
