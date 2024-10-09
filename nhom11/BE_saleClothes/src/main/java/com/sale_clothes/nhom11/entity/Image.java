package com.sale_clothes.nhom11.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private int imageId;

    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "spMa")
    private SanPham sanPham;
}
