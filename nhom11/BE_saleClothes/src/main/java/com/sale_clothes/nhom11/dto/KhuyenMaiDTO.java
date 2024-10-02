package com.sale_clothes.nhom11.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KhuyenMaiDTO {
    private String kh_ma;

    private String km_ten;

    private String km_noiDung;

    private LocalDate km_tuNgay;

    private LocalDate km_denNgay;
}
