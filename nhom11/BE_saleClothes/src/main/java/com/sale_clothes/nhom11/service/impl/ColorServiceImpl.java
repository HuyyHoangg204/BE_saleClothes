package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.ColorDTO;
import com.sale_clothes.nhom11.entity.Color;
import com.sale_clothes.nhom11.logger.ultil.Logger;
import com.sale_clothes.nhom11.mapper.ColorMapper;
import com.sale_clothes.nhom11.repository.ColorRepository;
import com.sale_clothes.nhom11.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorMapper colorMapper;


    @Override
    public ColorDTO create(ColorDTO dto) {

        Color color = colorMapper.mapToColor(dto);

        Color savedColor = colorRepository.save(color);

        var dtoReturn = colorMapper.mapToColorDTO(savedColor);



        return dtoReturn;
    }

    @Override
    public List<ColorDTO> getAll() {
        return null;
    }

    @Override
    public ColorDTO getById(int id) {
        return null;
    }

    @Override
    public ColorDTO update(int id, ColorDTO dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
