package com.sale_clothes.nhom11.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sale_clothes.nhom11.entity.GioHang;

public interface GioHangRepository extends JpaRepository<GioHang, Integer> {
    List<GioHang> findAllByUsername(String username);

    Optional<GioHang> findByUsernameAndProductIdAndSizeAndColorId(
            String username, int productId, String size, int colorId);

    @Modifying
    @Query("DELETE from GioHang c where c.username = :username and c.productId = :productId")
    void deleteByUsernameAndProductId(@Param("username") String userId, @Param("productId") int productId);
    @Modifying
    @Query("DELETE from GioHang c where c.username = :username")
    void deleteByUsername(@Param("username") String userId);
}
