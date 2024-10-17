package com.sale_clothes.nhom11.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sale_clothes.nhom11.entity.DanhMucCon;
import com.sale_clothes.nhom11.repository.DanhMucConRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.mapper.SanPhamMapper;
import com.sale_clothes.nhom11.repository.SanPhamRepository;
import com.sale_clothes.nhom11.service.SanPhamService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private DanhMucConRepository danhMucConRepository;


    @Override
    @Transactional
    public SanPhamDTO createSanPhamDTO(SanPhamDTO sanPhamDTO) {
        SanPham sanPham = SanPhamMapper.mapToSanPham(sanPhamDTO);
        SanPham savedSanPham = sanPhamRepository.save(sanPham);
        return SanPhamMapper.mapToSanPhamDTO(savedSanPham);
    }

    @Override
    public List<SanPhamDTO> getAllSanPhamDTOs() {
        List<SanPham> sanPhams = sanPhamRepository.findAll();
        ArrayList<SanPhamDTO> sanPhamDTOS = new ArrayList<SanPhamDTO>();
        for(SanPham sanPham : sanPhams) {
            sanPhamDTOS.add(SanPhamMapper.mapToSanPhamDTO(sanPham));
        }
        return sanPhamDTOS;
    }

    @Override
    public SanPhamDTO findSanPhamDTOById(String id) {
        return null;
    }

    @Override
    public void updateSanPhamDTO(Integer id, SanPhamDTO sanPhamDTO) {
        SanPham sanPham2 = SanPhamMapper.mapToSanPham(sanPhamDTO);
        Optional<SanPham> sanPham = sanPhamRepository.findById(id);
        if(sanPham.isPresent()) {
            SanPham sanPham1 = sanPham.get();
            sanPham1.setSpColor(sanPham2.getSpColor());
            sanPham1.setSpGia(sanPham2.getSpGia());
            sanPham1.setSpMoTaChiTiet(sanPham2.getSpMoTaChiTiet());
            sanPham1.setSpMoTaNgan(sanPham2.getSpMoTaNgan());
            sanPham1.setSpTen(sanPham2.getSpTen());
            sanPham1.setSpGiaCu(sanPham2.getSpGiaCu());
            sanPham1.setSpSoLuong(sanPham2.getSpSoLuong());
            sanPham1.setDmcMa(sanPham2.getDmcMa());
            if(sanPham.get().getDmcMa() != null) {
                Optional<DanhMucCon> danhMucCon = danhMucConRepository.findById(sanPham.get().getDmcMa().getDmcMa());
                sanPham1.setDmcMa(danhMucCon.get());
            }
            sanPhamRepository.save(sanPham1);
        }
    }

    @Override
    @Transactional
    public void deleteSanPhamDTOById(int id) {
        sanPhamRepository.deleteById(id);
    }
}
