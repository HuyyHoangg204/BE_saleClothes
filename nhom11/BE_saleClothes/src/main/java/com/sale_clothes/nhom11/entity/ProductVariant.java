package com.sale_clothes.nhom11.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "product_variant")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer variant_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private SanPham product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ElementCollection
    @CollectionTable(name = "product_size", joinColumns = @JoinColumn(name = "variant_id"))
    @Column(name = "size")
    private Set<String> size;
    private int stockQuantity;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileData> fileDataList = new ArrayList<>();


}
