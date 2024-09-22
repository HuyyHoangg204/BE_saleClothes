package com.sale_clothes.nhom11.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "danh_muc_con")
public class DanhMucCon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dmc_ma")
    private Integer dmc_ma;

    @Column(name = "dmc_ten")
    private String dmc_ten;

    @ManyToOne
    @JoinColumn(name = "dm_ma")
    private DanhMuc dm_ma;

}
