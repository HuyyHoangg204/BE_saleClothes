package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.DanhMucConDTO;
import com.sale_clothes.nhom11.entity.DanhMucCon;
import com.sale_clothes.nhom11.mapper.DanhMucConMapper;
import com.sale_clothes.nhom11.mapper.DanhMucMapper;
import com.sale_clothes.nhom11.repository.DanhMucConRepository;
import com.sale_clothes.nhom11.service.DanhMucConService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class DanhMucConServiceImpl implements DanhMucConService {
    @Autowired
    private DanhMucConRepository danhMucConRepository;
    @Override
    public DanhMucConDTO createDanhMucCon(DanhMucConDTO danhMucConDTO) {
        DanhMucCon danhMucCon = DanhMucConMapper.mapToDanhMucCon(danhMucConDTO);
        DanhMucCon savedDanhMucCon = danhMucConRepository.save(danhMucCon);
        return DanhMucConMapper.mapToDanhMucConDTO(savedDanhMucCon);
    }

    @Override
    public List<DanhMucConDTO> getAllDanhMucCon() {
        List<DanhMucCon> listDanhMucCon = danhMucConRepository.findAll();
        List<DanhMucConDTO> listDanhMucConDTO = new ArrayList<DanhMucConDTO>();
        for(DanhMucCon danhMucCon : listDanhMucCon) {
            listDanhMucConDTO.add(DanhMucConMapper.mapToDanhMucConDTO(danhMucCon));
        }
        return listDanhMucConDTO;
    }

    @Override
    public DanhMucConDTO getDanhMucConById(Integer id) {
        return null;
    }

    @Override
    public DanhMucConDTO updateDanhMucCon(Integer id, DanhMucConDTO danhMucConDTO) {
        return null;
    }

    @Override
    public void deleteDanhMucCon(DanhMucConDTO danhMucConDTO) {

    }
}
