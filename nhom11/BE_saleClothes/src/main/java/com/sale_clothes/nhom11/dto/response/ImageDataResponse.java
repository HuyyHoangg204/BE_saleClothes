package com.sale_clothes.nhom11.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDataResponse {
    private String name;
    private String type;
    private String imageUrl; // Chứa đường dẫn URL của hình ảnh
    private Integer maSp;
}
