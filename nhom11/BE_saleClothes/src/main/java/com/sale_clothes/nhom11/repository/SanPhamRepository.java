package com.sale_clothes.nhom11.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sale_clothes.nhom11.entity.SanPham;

import javax.swing.text.html.Option;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query("SELECT DISTINCT p FROM SanPham p LEFT JOIN FETCH p.productVariants v WHERE p IN :products")
    List<SanPham> findProductsWithVariants(@Param("products") List<SanPham> products);

    @Query("SELECT sp FROM SanPham sp WHERE sp.dmcMa.dmcMa = :idDmc")
    Page<SanPham> findAllByDmcId(@Param("idDmc") int id, Pageable pageable);




    //Get suggestions product when searching
    @Query("SELECT s.product_id, s.name, " +
            "CASE " +
            "WHEN s.name LIKE CONCAT(:keyword, '%') THEN 30 " +  // Tìm từ đầu
            "WHEN s.name LIKE CONCAT('%', :keyword, '%') THEN 20 " +  // Tìm ở giữa
            "ELSE 10 " +
            "END + (50 - LENGTH(s.name)) AS priority " +
            "FROM SanPham s " +
            "WHERE s.name LIKE CONCAT('%', :keyword, '%') " +
            "ORDER BY priority DESC " +
            "LIMIT 6")
    List<Object[]> searchProducts(@Param("keyword") String keyword);

    @Query("SELECT sp FROM SanPham sp WHERE sp.name LIKE CONCAT('%', :keyword, '%')")
    Page<SanPham> findAllBySearchLetter(@Param("keyword") String keyword, Pageable pageable);


    @Query("SELECT sp FROM SanPham sp JOIN sp.productVariants pv WHERE pv.variant_id = :id")
    Optional<SanPham> findByVariantId(@Param("id") Integer variantId);



}
