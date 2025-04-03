package com.sale_clothes.nhom11.service.impl;

import java.util.List;
import java.util.Optional;

import com.sale_clothes.nhom11.entity.KhuyenMai;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.mapper.KhuyenMaiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sale_clothes.nhom11.dto.KhuyenMaiDTO;
import com.sale_clothes.nhom11.repository.KhuyenMaiRepository;
import com.sale_clothes.nhom11.service.KhuyenMaiService;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    private KhuyenMaiRepository khuyenMaiRepository;
    private KhuyenMaiMapper khuyenMaiMapper;

    @Autowired
    public KhuyenMaiServiceImpl(KhuyenMaiRepository repo, KhuyenMaiMapper mp){
        this.khuyenMaiMapper = mp;
        this.khuyenMaiRepository = repo;
    }

    @Override
    @Transactional
    public KhuyenMaiDTO createKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai khuyenMai = khuyenMaiMapper.mapToKhuyenMai(khuyenMaiDTO);
        KhuyenMai saveKhuyenMai = khuyenMaiRepository.save(khuyenMai);
        return khuyenMaiMapper.mapToKhuyenMaiDTO(saveKhuyenMai);
    }

    @Override
    public List<KhuyenMaiDTO> getAllKhuyenMai(Integer indexPage) {
        Pageable page =  PageRequest.of(indexPage, 4);
        Page<KhuyenMai> khuyenMaiPage = khuyenMaiRepository.findAll(page);
        return khuyenMaiMapper.mapToListKhuyenMaiDTO(khuyenMaiPage.getContent());
    }

    @Override
    public Optional<KhuyenMaiDTO> getKhuyenMaiById(String id) {
        return khuyenMaiRepository.findById(id).map(khuyenMaiMapper::mapToKhuyenMaiDTO);
    }

    @Override
    @Transactional
    public KhuyenMaiDTO updateKhuyenMai(String id, KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Không tìm thấy voucher phù hợp")
        );
        khuyenMai = khuyenMaiMapper.mapToKhuyenMai(khuyenMaiDTO);
        khuyenMaiRepository.save(khuyenMai);
        return khuyenMaiDTO;
    }

    @Override
    public void deleteKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        khuyenMaiRepository.deleteById(khuyenMaiDTO.getId().toString());
    }
}
