package com.sale_clothes.nhom11.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ColorDTO {
    private int color_id;
    private String color_name;
    private String color_code;
}
