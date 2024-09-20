package com.sale_clothes.nhom11.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="chi_tiet_don_hang")
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ctdh_ma")
    private Integer ctdh_ma;

    @Column(name="soluong")
    private int soluong;

    @Column(name="size")
    private int size;

    @Column(name="theloai")
    private String theLoai;

    @Column(name="trangthaidonhang")
    private String trangThaiDonHang;

    @ManyToOne
    @JoinColumn(name="sp_ma")
    private SanPham sp_ma;

    @ManyToOne
    @JoinColumn(name = "dh_ma")
    private DonDatHang dh_ma;
}
