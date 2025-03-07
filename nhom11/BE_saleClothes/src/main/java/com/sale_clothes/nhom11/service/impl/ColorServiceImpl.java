package com.sale_clothes.nhom11.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sale_clothes.nhom11.dto.ColorDTO;
import com.sale_clothes.nhom11.entity.Color;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.mapper.ColorMapper;
import com.sale_clothes.nhom11.repository.ColorRepository;
import com.sale_clothes.nhom11.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorMapper colorMapper;

    @Override
    public ColorDTO create(ColorDTO dto) {
        if (dto.getColorName().isEmpty()) {
            throw new RuntimeException("Vui lòng nhập tên màu!!");
        }
        if (dto.getColorCode().isEmpty()) {
            throw new RuntimeException("Vui lòng nhập mã màu!!!");
        }
        if (!isHexColor(dto.getColorCode())) {
            throw new RuntimeException("Mã màu không hợp lệ! Vui lòng nhập mã màu HEX hợp lệ.");
        }

        if (colorRepository.existsByColorCode(dto.getColorCode())) {
            throw new RuntimeException("Mã màu đã tồn tai trong hệ thống!!");
        }

        Color color = colorMapper.mapToColor(dto);

        Color savedColor = colorRepository.save(color);

        var dtoReturn = colorMapper.mapToColorDTO(savedColor);

        return dtoReturn;
    }

    @Override
    public List<ColorDTO> getAll() {
        List<Color> colorList = colorRepository.findAll();
        List<ColorDTO> colorDTOList = new ArrayList<>();
        for (Color color : colorList) {
            colorDTOList.add(colorMapper.mapToColorDTO(color));
        }
        return colorDTOList;
    }

    @Override
    public ColorDTO getById(Integer id) {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            return colorMapper.mapToColorDTO(color.get());
        } else throw new NotFoundException("Could not find color");
    }

    @Override
    public void update(Integer id, ColorDTO dto) {}

    @Override
    public void delete(Integer id) {}

    public boolean isHexColor(String colorCode) {
        // Kiểm tra nếu mã màu có dạng # và theo sau là 6 ký tự hợp lệ (0-9, A-F, a-f)
        String regex = "^#([A-Fa-f0-9]{3}|[A-Fa-f0-9]{6})$";
        return colorCode.matches(regex);
    }
}
