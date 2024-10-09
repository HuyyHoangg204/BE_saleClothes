package com.sale_clothes.nhom11.dto;

import com.sale_clothes.nhom11.entity.SanPham;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private int imageId;
    private String name;
    private String type;
    private byte[] imageData;
    private Integer spMa;

}
