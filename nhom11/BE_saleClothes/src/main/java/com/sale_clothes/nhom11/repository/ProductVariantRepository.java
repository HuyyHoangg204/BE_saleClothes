package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant,Integer> {
    @Query("select pv from ProductVariant pv join Color c on pv.color.colorID = c.colorID where pv.product.product_id = :productID ")
    List<ProductVariant> getAllByProductID(@Param("productID") int productID);

}
