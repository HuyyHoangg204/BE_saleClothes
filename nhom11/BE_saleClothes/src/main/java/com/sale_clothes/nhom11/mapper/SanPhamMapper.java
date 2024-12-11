package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.SanPhamDTO;
import com.sale_clothes.nhom11.entity.DanhMucCon;
import com.sale_clothes.nhom11.entity.SanPham;
<<<<<<< HEAD

public class SanPhamMapper {
    public static SanPhamDTO mapToSanPhamDTO(SanPham sanPham) {
        return SanPhamDTO.builder()
                .spMa(sanPham.getSpMa())
                .spTen(sanPham.getSpTen())
                .spGia(sanPham.getSpGia())
                .spGiaCu(sanPham.getSpGiaCu())
                .spMoTaChiTiet(sanPham.getSpMoTaChiTiet())
                .spColor(sanPham.getSpColor())
                .spMoTaNgan(sanPham.getSpMoTaNgan())
                .spNgayCapNhat(sanPham.getSpNgayCapNhat())
                .spSoLuong(sanPham.getSpSoLuong())
                .dmcMa(sanPham.getDmcMa().getDmcMa())
                .build();
    }

    public static SanPham mapToSanPham(SanPhamDTO sanPhamDTO) {
        DanhMucCon dmc = new DanhMucCon();
        dmc.setDmcMa(sanPhamDTO.getDmcMa());

        return SanPham.builder()
                .spMa(sanPhamDTO.getSpMa())
                .spColor(sanPhamDTO.getSpColor())
                .spGia(sanPhamDTO.getSpGia())
                .spGiaCu(sanPhamDTO.getSpGiaCu())
                .spTen(sanPhamDTO.getSpTen())
                .spMoTaChiTiet(sanPhamDTO.getSpMoTaChiTiet())
                .spMoTaNgan(sanPhamDTO.getSpMoTaNgan())
                .spNgayCapNhat(sanPhamDTO.getSpNgayCapNhat())
                .imageMain(sanPhamDTO.getImageMain())
                .dmcMa(dmc)
                .spSoLuong(sanPhamDTO.getSpSoLuong())
                .build();
    }
=======
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

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

>>>>>>> 1cd856ad (build: ProductVariant, Color entity)
}
