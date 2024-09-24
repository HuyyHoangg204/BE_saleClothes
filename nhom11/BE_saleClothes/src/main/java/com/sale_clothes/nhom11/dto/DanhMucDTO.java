package com.sale_clothes.nhom11.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DanhMucDTO {
    private Integer dm_ma;


    private String dm_ten;
}
