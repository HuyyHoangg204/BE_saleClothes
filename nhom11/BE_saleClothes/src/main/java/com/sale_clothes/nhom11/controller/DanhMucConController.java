package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.DanhMucConDTO;
import com.sale_clothes.nhom11.dto.DanhMucDTO;
import com.sale_clothes.nhom11.entity.DanhMucCon;
import com.sale_clothes.nhom11.service.impl.DanhMucConServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DanhMucConController {
    @Autowired
    private DanhMucConServiceImpl danhMucConServiceImpl;
    @PostMapping("/danhmuccons")
    public ResponseEntity<DanhMucConDTO> createDanhMucCon(@RequestBody DanhMucConDTO danhMucConDTO) {
        DanhMucConDTO danhMucConDTO1 = danhMucConServiceImpl.createDanhMucCon(danhMucConDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(danhMucConDTO1);
    }
    @GetMapping("/danhmuccons")
    public ResponseEntity<List<DanhMucConDTO>> getAllDanhMucConDTOs ( ) {
        List<DanhMucConDTO> danhMucConDTOList = danhMucConServiceImpl.getAllDanhMucCon();
        return ResponseEntity.ok(danhMucConDTOList);
    }
}
