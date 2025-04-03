package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.KhuyenMaiDTO;
import com.sale_clothes.nhom11.entity.KhuyenMai;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring", nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface KhuyenMaiMapper {
    @Mapping(target = "code", ignore = true)
    public KhuyenMai mapToKhuyenMai(KhuyenMaiDTO kmdto);
    @Mapping(target = "code")
    public KhuyenMaiDTO mapToKhuyenMaiDTO(KhuyenMai km);
    @Mapping(target = "code", ignore = true)
    public List<KhuyenMaiDTO> mapToListKhuyenMaiDTO(List<KhuyenMai> kmdto);
    @Mapping(target = "code")
    public List<KhuyenMai> mapToListKhuyenMai(List<KhuyenMaiDTO> kmdto);
}
