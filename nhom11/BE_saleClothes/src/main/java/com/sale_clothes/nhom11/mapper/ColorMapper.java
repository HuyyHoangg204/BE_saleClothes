package com.sale_clothes.nhom11.mapper;

import org.mapstruct.Mapper;

import com.sale_clothes.nhom11.dto.ColorDTO;
import com.sale_clothes.nhom11.entity.Color;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    ColorDTO mapToColorDTO(Color color);

    Color mapToColor(ColorDTO colorDTO);
}
