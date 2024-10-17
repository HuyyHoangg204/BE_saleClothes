package com.sale_clothes.nhom11.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale_clothes.nhom11.dto.KhuyenMaiDTO;
import com.sale_clothes.nhom11.entity.KhuyenMai;
import com.sale_clothes.nhom11.mapper.KhuyenMaiMapper;
import com.sale_clothes.nhom11.repository.KhuyenMaiRepository;
import com.sale_clothes.nhom11.service.KhuyenMaiService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {
    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Override
    @Transactional
    public KhuyenMaiDTO createKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai khuyenMai = KhuyenMaiMapper.mapToKhuyenMai(khuyenMaiDTO);
        KhuyenMai saveKhuyenMai = khuyenMaiRepository.save(khuyenMai);
        return KhuyenMaiMapper.mapToKhuyenMaiDTO(saveKhuyenMai);
    }

    @Override
    public List<KhuyenMaiDTO> getAllKhuyenMai() {
        return null;
    }

    @Override
    public KhuyenMaiDTO getKhuyenMaiById(String id) {
        return null;
    }

    @Override
    public KhuyenMaiDTO updateKhuyenMai(String id, KhuyenMaiDTO khuyenMaiDTO) {
        return null;
    }

    @Override
    public void deleteKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {}
}
