package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.ImageDTO;
import com.sale_clothes.nhom11.entity.Image;
import com.sale_clothes.nhom11.entity.SanPham;

public class ImageMapper {
    public static Image maptoImage(ImageDTO imageDTO) {
        SanPham sanPham = new SanPham();
        sanPham.setSpMa(imageDTO.getSpMa());
        return Image.builder()
                .imageId(imageDTO.getImageId())
                .imageData(imageDTO.getImageData())
                .sanPham(sanPham)
                .name(imageDTO.getName())
                .type(imageDTO.getType())
                .build();
    }

    public static ImageDTO mapToImageDTO(Image image) {
        return ImageDTO.builder()
                .imageId(image.getImageId())
                .imageData(image.getImageData())
                .name(image.getName())
                .type(image.getType())
                .spMa(image.getSanPham().getSpMa())
                .build();
    }
}
