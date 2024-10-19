package com.sale_clothes.nhom11.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gh_ma")
    private Integer ghMa;

    @OneToOne
    @JoinColumn(name = "kh_username")
    private KhachHang khUserName;
}
