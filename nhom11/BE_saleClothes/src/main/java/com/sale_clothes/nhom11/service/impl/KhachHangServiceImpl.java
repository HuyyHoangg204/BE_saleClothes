package com.sale_clothes.nhom11.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.entity.KhachHang;
import com.sale_clothes.nhom11.enums.Role;
import com.sale_clothes.nhom11.exception.AppException;
import com.sale_clothes.nhom11.exception.ErrorCode;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.mapper.KhachHangMapper;
import com.sale_clothes.nhom11.repository.KhachHangRepository;
import com.sale_clothes.nhom11.service.KhachHangService;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public KhachHangDTO createKhachHang(KhachHangDTO khachHangDTO) {
        KhachHang khachHang = KhachHangMapper.mapToKhachHang(khachHangDTO);
        if (khachHangRepository.existsByKhUserName(khachHang.getKhUserName())) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTED);
        }
        if (khachHangRepository.existsByKhEmail(khachHang.getKhEmail())) {
            throw new AppException(ErrorCode.EMAIL_AlREADY_EXISTED);
        }
        // Encode password
        khachHangDTO.setKhPassWord(passwordEncoder.encode(khachHangDTO.getKhPassWord()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.CUSTOMER.name());
        khachHang.setRoles(roles);
        KhachHang savedKhachHang = khachHangRepository.save(khachHang);
        return KhachHangMapper.mapToKhachHangDTO(savedKhachHang);
    }

    @Override
    public List<KhachHangDTO> getAllKhachHang() {
        List<KhachHang> listKhachHang = khachHangRepository.findAll();
        ArrayList<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
        for (KhachHang khachHang : listKhachHang) {
            listKhachHangDTO.add(KhachHangMapper.mapToKhachHangDTO(khachHang));
        }
        return listKhachHangDTO;
    }

    @Override
    public KhachHangDTO updateKhachHang(String id, KhachHangDTO khachHangDTO) {
        return null;
    }

    @Override
    public KhachHangDTO getKhachHangById(String username) {
        List<KhachHang> listKhachHang = khachHangRepository.findAll();
        for (KhachHang khachHang : listKhachHang) {
            KhachHangDTO khachHangDTO = KhachHangMapper.mapToKhachHangDTO(khachHang);
            if (username.equals((khachHangDTO.getKhUserName()))) {
                return khachHangDTO;
            }
        }
        throw new NotFoundException("Không tìm thấy user");
    }

    @Override
    public KhachHangDTO getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        KhachHang khachHang =
                khachHangRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        return KhachHangMapper.mapToKhachHangDTO(khachHang);
    }

    @Override
    public void deleteKhachHang(String id) {}
}
