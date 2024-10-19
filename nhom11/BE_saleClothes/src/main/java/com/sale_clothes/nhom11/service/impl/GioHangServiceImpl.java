package com.sale_clothes.nhom11.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sale_clothes.nhom11.dto.GioHangDTO;
import com.sale_clothes.nhom11.entity.GioHang;
import com.sale_clothes.nhom11.mapper.GioHangMapper;
import com.sale_clothes.nhom11.repository.GioHangRepository;
import com.sale_clothes.nhom11.service.GioHangService;

@Service
public class GioHangServiceImpl implements GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    @Transactional
    public GioHangDTO createGioHang(GioHangDTO gioHangDTO) {
        GioHang gioHang = GioHangMapper.mapToGioHang(gioHangDTO);
        GioHang savedGioHang = gioHangRepository.save(gioHang);
        return GioHangMapper.mapToGioHangDTO(savedGioHang);
    }

    @Override
    public List<GioHangDTO> getAllGioHang() {
        return null;
    }

    @Override
    public GioHangDTO getGioHangId(Integer id) {
        return null;
    }

    @Override
    public GioHangDTO updateGioHang(Integer id, GioHangDTO gioHangDTO) {
        return null;
    }

    @Override
    public void deleteGioHang(GioHangDTO gioHangDTO) {}
}
