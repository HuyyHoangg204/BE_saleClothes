package com.sale_clothes.nhom11.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="loai_san_pham")
public class LoaiSanPham {
    @Id
    @Column(name="lsp_ma")
    private String lsp_ma;

    @Column(name="lsp_ten")
    private String lsp_ten;

    @Column(name = "lsp_mota")
    private String lsp_mota;
}
