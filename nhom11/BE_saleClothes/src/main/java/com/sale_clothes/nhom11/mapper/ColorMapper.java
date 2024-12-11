package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.ColorDTO;
import com.sale_clothes.nhom11.entity.Color;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    ColorDTO mapToColorDTO(Color color);

    Color mapToColor(ColorDTO colorDTO);
}

