package com.sale_clothes.nhom11.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ColorDTO {
    private int colorID;
    private String colorName;
    private String colorCode;
}
