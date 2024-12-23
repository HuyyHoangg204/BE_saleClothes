package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant,Integer> {
    @Query("SELECT productVariant FROM ProductVariant  productVariant where productVariant.product.product_id  = :productID")
    List<ProductVariant> getAllByProductID(@Param("productID") int productID);

}
