package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.entity.KhuyenMai;
import com.sale_clothes.nhom11.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.KhuyenMaiDTO;
import com.sale_clothes.nhom11.service.impl.KhuyenMaiServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/khuyen-mai")
public class KhuyenMaiController {
    @Autowired
    private KhuyenMaiServiceImpl khuyenMaiServiceImpl;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<KhuyenMaiDTO>> getKhuyenMai(@RequestParam(defaultValue = "0") Integer page){
        List<KhuyenMaiDTO> saveKhuyenMaiDTO = khuyenMaiServiceImpl.getAllKhuyenMai(page);
        return new ResponseEntity<>(saveKhuyenMaiDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Optional<KhuyenMaiDTO> getById(@PathVariable String id){
        return khuyenMaiServiceImpl.getKhuyenMaiById(id) ;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KhuyenMaiDTO> createKhuyenMai(@RequestBody KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMaiDTO saveKhuyenMai = khuyenMaiServiceImpl.createKhuyenMai(khuyenMaiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveKhuyenMai);
    }
    @PutMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KhuyenMaiDTO> editKhuyenMai(@RequestBody KhuyenMaiDTO khuyenMaiDTO){
        KhuyenMaiDTO saveKhuyenMai = khuyenMaiServiceImpl.updateKhuyenMai(khuyenMaiDTO.getId().toString(), khuyenMaiDTO);
        return new ResponseEntity<KhuyenMaiDTO>(saveKhuyenMai, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    @PreAuthorize(("hasRole('ADMIN')"))
    public void deleteKhuyenMai(@RequestBody KhuyenMaiDTO khuyenMaiDTO){
        khuyenMaiServiceImpl.deleteKhuyenMai(khuyenMaiDTO);
    }
}
