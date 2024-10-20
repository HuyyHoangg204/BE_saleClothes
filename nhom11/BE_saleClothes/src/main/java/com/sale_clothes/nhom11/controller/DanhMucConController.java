package com.sale_clothes.nhom11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.DanhMucConDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.impl.DanhMucConServiceImpl;

@RestController
@RequestMapping("/api")
public class DanhMucConController {
    @Autowired
    private DanhMucConServiceImpl danhMucConServiceImpl;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-danhmuccon")
    public ApiResponse<DanhMucConDTO> createDanhMucCon(@RequestBody DanhMucConDTO danhMucConDTO) {
        DanhMucConDTO danhMucConDTO1 = danhMucConServiceImpl.createDanhMucCon(danhMucConDTO);
        return ApiResponse.<DanhMucConDTO>builder().result(danhMucConDTO1).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/danhmuccons")
    public ApiResponse<List<DanhMucConDTO>> getAllDanhMucCon() {
        List<DanhMucConDTO> danhMucConDTOS = danhMucConServiceImpl.getAllDanhMucCon();
        return ApiResponse.<List<DanhMucConDTO>>builder().result(danhMucConDTOS).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/danhmuccon/{dmma}")
    public ApiResponse<List<DanhMucConDTO>> getDanhMucConByDmMa(@PathVariable Integer dmma) {
        List<DanhMucConDTO> danhMucConDTOS = danhMucConServiceImpl.getDanhMucConByDmMa(dmma);
        return ApiResponse.<List<DanhMucConDTO>>builder().result(danhMucConDTOS).build();
    }
}
