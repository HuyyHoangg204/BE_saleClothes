package com.sale_clothes.nhom11.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "khuyen_mai")
public class KhuyenMai {
    @Id
    @Column(name = "km_ma")
    private String kh_ma;

    @Column(name = "km_ten")
    private String km_ten;

    @Column(name = "km_noidung")
    private String km_noiDung;

    @Column(name = "km_tungay")
    private LocalDate km_tuNgay;

    @Column(name = "km_denngay")
    private LocalDate km_denNgay;

}
