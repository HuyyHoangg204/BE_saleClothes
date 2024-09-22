package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.LoaiSanPhamDTO;
import com.sale_clothes.nhom11.entity.LoaiSanPham;
import com.sale_clothes.nhom11.mapper.LoaiSanPhamMapper;
import com.sale_clothes.nhom11.repository.LoaiSanPhamRepository;
import com.sale_clothes.nhom11.service.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
    @Autowired
    private LoaiSanPhamRepository loaiSanPhamRepository;
    @Override
    public LoaiSanPhamDTO createloaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO) {
        LoaiSanPham loaiSanPham = LoaiSanPhamMapper.mapToLoaiSanPham(loaiSanPhamDTO);
        LoaiSanPham savedLoaiSanPham = loaiSanPhamRepository.save(loaiSanPham);
        return LoaiSanPhamMapper.mapToLoaiSanPhamDTO(savedLoaiSanPham);
    }

    @Override
    public List<LoaiSanPhamDTO> getAllLoaiSanPham() {
        return null;
    }

    @Override
    public LoaiSanPhamDTO getLoaiSanPhamById(String Id) {
        return null;
    }

    @Override
    public LoaiSanPhamDTO updateLoaiSanPham(String Id, LoaiSanPhamDTO loaiSanPhamDTO) {
        return null;
    }

    @Override
    public void deleteLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO) {

    }
}
