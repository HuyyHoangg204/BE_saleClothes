package com.sale_clothes.nhom11.controller;

import java.util.List;

import com.sale_clothes.nhom11.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.DanhMucConDTO;
import com.sale_clothes.nhom11.service.impl.DanhMucConServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DanhMucConController {
    @Autowired
    private DanhMucConServiceImpl danhMucConServiceImpl;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-danhmuccon")
    public ApiResponse<DanhMucConDTO> createDanhMucCon(@RequestBody DanhMucConDTO danhMucConDTO) {
        DanhMucConDTO danhMucConDTO1 = danhMucConServiceImpl.createDanhMucCon(danhMucConDTO);
        return ApiResponse.<DanhMucConDTO>builder()
                .result(danhMucConDTO1)
                .build();
    }

    @GetMapping("/danhmuccons")
    public ResponseEntity<List<DanhMucConDTO>> getAllDanhMucConDTOs() {
        List<DanhMucConDTO> danhMucConDTOList = danhMucConServiceImpl.getAllDanhMucCon();
        return ResponseEntity.ok(danhMucConDTOList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/danhmuccon/{dmma}")
    public ApiResponse<List<DanhMucConDTO>> getDanhMucConByDmMa(@PathVariable Integer dmma) {
        List<DanhMucConDTO> danhMucConDTOS = danhMucConServiceImpl.getDanhMucConByDmMa(dmma);
        return ApiResponse.<List<DanhMucConDTO>>builder()
                .result(danhMucConDTOS)
                .build();
    }
}
