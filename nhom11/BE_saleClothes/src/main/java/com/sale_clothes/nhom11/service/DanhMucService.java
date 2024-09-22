package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.DanhMucDTO;
import com.sale_clothes.nhom11.repository.DanhMucRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DanhMucService {
    DanhMucDTO createDanhMucDTO(DanhMucDTO danhMucDTO);
    List<DanhMucDTO> findALLDanhMucDTOs();
    DanhMucDTO findDanhMucDTOById(Integer id);
    DanhMucDTO updateDanhMucDTO(Integer id, DanhMucDTO danhMucDTO);
    void delelteDanhMucDTOs(DanhMucDTO danhMucDTO);
}
