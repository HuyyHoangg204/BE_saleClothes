package com.sale_clothes.nhom11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sale_clothes.nhom11.entity.SanPham;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query("SELECT DISTINCT p FROM SanPham p LEFT JOIN FETCH p.productVariants v WHERE p IN :products")
    List<SanPham> findProductsWithVariants(@Param("products") List<SanPham> products);
}
