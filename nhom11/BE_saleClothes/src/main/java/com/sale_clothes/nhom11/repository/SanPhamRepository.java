package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.ProductVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sale_clothes.nhom11.entity.SanPham;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query("SELECT DISTINCT p FROM SanPham p LEFT JOIN FETCH p.productVariants v WHERE p IN :products")
    List<SanPham> findProductsWithVariants(@Param("products") List<SanPham> products);



}
