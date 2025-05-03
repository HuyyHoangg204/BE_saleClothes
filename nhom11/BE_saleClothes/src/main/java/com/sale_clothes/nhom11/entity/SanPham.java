package com.sale_clothes.nhom11.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private String name;
    private double base_price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String material;
    private String instruction;
    private int discount_percentage;
    private String product_code;

    @ManyToOne
    @JoinColumn(name = "dmc_ma")
    private DanhMucCon dmcMa;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> productVariants = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private DonDatHang order;

    //    @ManyToOne(optional = true)
    //    @JoinColumn(name = "km_ma")
    //    private KhuyenMai khMa;

    //    @ManyToOne(optional = true)
    //    @JoinColumn(name = "lsp_ma")
    //    private LoaiSanPham lspMa;
    //
    //    @ManyToOne(optional = true)
    //    @JoinColumn(name = "ch_ma")
    //    private CuaHang chMa;
    
}
