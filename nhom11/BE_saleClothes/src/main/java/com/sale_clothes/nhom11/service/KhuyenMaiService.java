package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.KhuyenMaiDTO;
import com.sale_clothes.nhom11.repository.KhuyenMaiRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KhuyenMaiService {

    KhuyenMaiDTO createKhuyenMai(KhuyenMaiDTO khuyenMaiDTO);
    List<KhuyenMaiDTO> getAllKhuyenMai();
    KhuyenMaiDTO getKhuyenMaiById(String id);
    KhuyenMaiDTO updateKhuyenMai(String id, KhuyenMaiDTO khuyenMaiDTO);
    void deleteKhuyenMai(KhuyenMaiDTO khuyenMaiDTO);
}
