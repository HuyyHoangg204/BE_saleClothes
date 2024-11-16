package com.sale_clothes.nhom11.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;
    private String name;
    private double base_price;
    private String description;
    private String material;
    private String instruction;
    private boolean is_discounted;
    private int discount_percentage;
    private String product_code;

    @ManyToOne
    @JoinColumn(name = "dmc_ma")
    private DanhMucCon dmcMa;

    @ManyToOne
    @JoinColumn(name = "km_ma")
    private KhuyenMai khMa;

    @ManyToOne
    @JoinColumn(name = "lsp_ma")
    private LoaiSanPham lspMa;

    @ManyToOne
    @JoinColumn(name = "ch_ma")
    private CuaHang chMa;

}
