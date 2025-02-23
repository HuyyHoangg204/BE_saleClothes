package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct,Long> {
    @Query("SELECT COUNT(fp) > 0 FROM FavoriteProduct fp " +
            "WHERE fp.khachHang.khUserName = :username " +
            "AND fp.sanPham.product_id = :productId")
    boolean existsByUserAndProduct(@Param("username") String username,
                                   @Param("productId") int productId);

    @Query("select fp from FavoriteProduct fp where fp.khachHang.khUserName = :username")
    List<FavoriteProduct> findAllByUsername(@Param("username") String username);

    @Query("SELECT COUNT(fp) > 0 FROM FavoriteProduct fp " +
            "WHERE fp.khachHang.khUserName = :username ")
    boolean existsByUser(@Param("username") String username
                                   );

    @Modifying
    @Query("delete FavoriteProduct fp where fp.khachHang.khUserName = :username and fp.sanPham.product_id = :productId ")
    void deleteByUsernameAndProduct(@Param("username") String username, @Param("productId") int productId);
}
