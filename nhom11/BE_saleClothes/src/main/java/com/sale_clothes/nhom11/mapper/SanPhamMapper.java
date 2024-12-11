package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.SanPhamDTO;

import com.sale_clothes.nhom11.entity.SanPham;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface SanPhamMapper {

   @Mapping(source = "dmcMaId", target = "dmcMa.dmcMa") // ánh xạ ID thành entity
//   @Mapping(source = "kmMaId", target = "khMa.km_ma")
//   @Mapping(source = "lspMaId", target = "lspMa.lsp_ma")
//   @Mapping(source = "chMaId", target = "chMa.ch_ma")
   SanPham mapToSanPham(SanPhamDTO sanPhamDTO);

      // Ánh xạ từ chMa (CuaHang) sang chMaId trong DTO
      @Mapping(source = "dmcMa.dmcMa", target = "dmcMaId") // dmcMa là entity, ánh xạ ID
//      @Mapping(source = "khMa.km_ma", target = "kmMaId")  // khMa là entity, ánh xạ ID
//      @Mapping(source = "lspMa.lsp_ma", target = "lspMaId") // lspMa là entity, ánh xạ ID
//      @Mapping(source = "chMa.ch_ma", target = "chMaId")  // chMa là entity, ánh xạ ID
   SanPhamDTO mapToSanPhamDTO(SanPham sanPham);


}
