package com.sale_clothes.nhom11.mapper;


import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.entity.SanPham;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SanPhamMapper {
   SanPhamMapper INSTANCE = Mappers.getMapper(SanPhamMapper.class);

   SanPham mapToSanPham(SanPhamDTO sanPhamDTO);

   @Mapping(source = "dmcMa.id", target = "dmcMaId")   // Ánh xạ từ dmcMa (DanhMucCon) sang dmcMaId trong DTO
   @Mapping(source = "kmMa.id", target = "khMaId")       // Ánh xạ từ khMa (KhuyenMai) sang khMaId trong DTO
   @Mapping(source = "lspMa.id", target = "lspMaId")     // Ánh xạ từ lspMa (LoaiSanPham) sang lspMaId trong DTO
   @Mapping(source = "chMa.id", target = "chMaId")       // Ánh xạ từ chMa (CuaHang) sang chMaId trong DTO
   SanPhamDTO mapToSanPhamDTO(SanPham sanPham);
}
