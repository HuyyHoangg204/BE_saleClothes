package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.ColorDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.logger.ultil.Logger;
import com.sale_clothes.nhom11.service.impl.ColorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ColorController {
    @Autowired
    private ColorServiceImpl colorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add_color")
    public ApiResponse<ColorDTO> addColor(@RequestBody ColorDTO colorDTO) {

        ColorDTO colorDTO1 = colorService.create(colorDTO);
        return ApiResponse.<ColorDTO>builder()
                .result(colorDTO1)
                .build();
    }

    @GetMapping("/colors")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<ColorDTO>> getAllColor() {
        List<ColorDTO> colorDTOList = colorService.getAll();
        return ApiResponse.<List<ColorDTO>>builder()
                .result(colorDTOList)
                .build();
    }

    @GetMapping("/color/{id}")
    public ApiResponse<ColorDTO> getColorById(@PathVariable int id) {
        ColorDTO colorDTO = colorService.getById(id);
        return ApiResponse.<ColorDTO>builder()
                .result(colorDTO)
                .build();
    }
}
