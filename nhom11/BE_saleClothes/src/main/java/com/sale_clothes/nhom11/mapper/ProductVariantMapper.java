package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(source = "product.product_id", target = "product_id")
    @Mapping(source = "color.color_id", target = "color_id")
    ProductVariantDTO mapToProductVariantDTO(ProductVariant productVariant);

    @Mapping(source = "product_id", target = "product.product_id")
    @Mapping(source = "color_id", target = "color.color_id")
    ProductVariant mapToProductVariant(ProductVariantDTO productVariantDTO);
}
