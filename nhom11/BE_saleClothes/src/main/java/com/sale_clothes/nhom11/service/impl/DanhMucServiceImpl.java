package com.sale_clothes.nhom11.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale_clothes.nhom11.dto.DanhMucDTO;
import com.sale_clothes.nhom11.entity.DanhMuc;
import com.sale_clothes.nhom11.mapper.DanhMucMapper;
import com.sale_clothes.nhom11.repository.DanhMucRepository;
import com.sale_clothes.nhom11.service.DanhMucService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DanhMucServiceImpl implements DanhMucService {
    @Autowired
    private DanhMucRepository danhMucRepository;

    @Override
    public DanhMucDTO createDanhMucDTO(DanhMucDTO danhMucDTO) {
        DanhMuc danhMuc = DanhMucMapper.mapToDanhMuc(danhMucDTO);
        DanhMuc savedDanhMuc = danhMucRepository.save(danhMuc);
        return DanhMucMapper.mapToDanhMucDTO(savedDanhMuc);
    }

    @Override
    public List<DanhMucDTO> findALLDanhMucDTOs() {
        List<DanhMucDTO> listDanhMucDTOs = new ArrayList<DanhMucDTO>();
        List<DanhMuc> listDanhMuc = danhMucRepository.findAll();
        for (DanhMuc danhMuc : listDanhMuc) {
            listDanhMucDTOs.add(DanhMucMapper.mapToDanhMucDTO(danhMuc));
        }
        return listDanhMucDTOs;
    }

    @Override
    public DanhMucDTO findDanhMucDTOById(Integer id) {
        return null;
    }

    @Override
    public DanhMucDTO updateDanhMucDTO(Integer id, DanhMucDTO danhMucDTO) {
        return null;
    }

    @Override
    public void delelteDanhMucDTOs(DanhMucDTO danhMucDTO) {}
}
