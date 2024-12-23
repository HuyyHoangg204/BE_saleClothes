package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(source = "product.product_id", target = "product_id")
    @Mapping(source = "color.colorID", target = "color_id")
    ProductVariantDTO mapToProductVariantDTO(ProductVariant productVariant);

    @Mapping(source = "product_id", target = "product.product_id")
    @Mapping(source = "color_id", target = "color.colorID")
    ProductVariant mapToProductVariant(ProductVariantDTO productVariantDTO);
}
