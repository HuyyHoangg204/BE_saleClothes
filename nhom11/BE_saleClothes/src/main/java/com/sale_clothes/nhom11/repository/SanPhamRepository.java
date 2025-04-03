package com.sale_clothes.nhom11.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sale_clothes.nhom11.entity.SanPham;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query("SELECT DISTINCT p FROM SanPham p LEFT JOIN FETCH p.productVariants v WHERE p IN :products")
    List<SanPham> findProductsWithVariants(@Param("products") List<SanPham> products);

    @Query("SELECT sp FROM SanPham sp WHERE sp.dmcMa.dmcMa = :idDmc")
    Page<SanPham> findAllByDmcId(@Param("idDmc") int id, Pageable pageable);
}
