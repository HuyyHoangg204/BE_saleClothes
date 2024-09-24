package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.DanhMucDTO;
import com.sale_clothes.nhom11.entity.DanhMuc;
import com.sale_clothes.nhom11.service.impl.DanhMucServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DanhMucController {
    @Autowired
    private DanhMucServiceImpl danhMucServiceImpl;
    @PostMapping("/danhmucs")
    public ResponseEntity<DanhMucDTO> createDanhMuc(@RequestBody DanhMucDTO danhMucDTO) {
        DanhMucDTO saveDanhMucDTO = danhMucServiceImpl.createDanhMucDTO(danhMucDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(saveDanhMucDTO);
    }
    @GetMapping("/danhmucs")
    public ResponseEntity<List<DanhMucDTO>> getAllDanhMuc() {
        List<DanhMucDTO> danhMucDTOS = danhMucServiceImpl.findALLDanhMucDTOs();
        return ResponseEntity.status(HttpStatus.OK).body(danhMucDTOS);
    }

}
