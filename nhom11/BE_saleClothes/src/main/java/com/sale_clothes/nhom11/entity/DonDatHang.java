package com.sale_clothes.nhom11.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "don_dat_hang")
public class DonDatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dh_ma")
    private Integer dh_ma;

    @Column(name = "dh_ngaylap")
    private LocalDate dh_ngayLap;

    @Column(name = "dh_ngaygiao")
    private LocalDate dh_ngayGiao;

    @Column(name = "dh_noigiao")
    private String dh_noiGiao;

    @Column(name = "dh_trangthaithanhtoan")
    private int dh_trangThaiThanhToan;

    @ManyToOne
    @JoinColumn(name = "kh_username")
    private KhachHang kh_userName;

    @OneToMany(mappedBy = "httt_ma")

    private List<HinhThucThanhToan> httt_ma;
}
