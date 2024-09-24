package com.sale_clothes.nhom11.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
