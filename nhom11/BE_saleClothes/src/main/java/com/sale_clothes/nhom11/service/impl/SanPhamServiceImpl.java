package com.sale_clothes.nhom11.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.mapper.SanPhamMapper;
import com.sale_clothes.nhom11.repository.SanPhamRepository;
import com.sale_clothes.nhom11.service.SanPhamService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public SanPhamDTO createSanPhamDTO(SanPhamDTO sanPhamDTO) {
        SanPham sanPham = SanPhamMapper.mapToSanPham(sanPhamDTO);
        SanPham savedSanPham = sanPhamRepository.save(sanPham);
        return SanPhamMapper.mapToSanPhamDTO(savedSanPham);
    }

    @Override
    public List<SanPhamDTO> getAllSanPhamDTOs() {
        return null;
    }

    @Override
    public SanPhamDTO findSanPhamDTOById(String id) {
        return null;
    }

    @Override
    public SanPhamDTO updateSanPhamDTO(String id, SanPhamDTO sanPhamDTO) {
        return null;
    }

    @Override
    public void deleteSanPhamDTO(SanPhamDTO sanPham) {}
}
