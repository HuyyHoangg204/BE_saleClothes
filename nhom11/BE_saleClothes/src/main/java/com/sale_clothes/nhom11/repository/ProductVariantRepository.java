package com.sale_clothes.nhom11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sale_clothes.nhom11.entity.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    @Query(
            "select pv from ProductVariant pv join Color c on pv.color.colorID = c.colorID where pv.product.product_id = :productID ")
    List<ProductVariant> getAllByProductID(@Param("productID") int productID);

    @Query("SELECT DISTINCT v FROM ProductVariant v LEFT JOIN FETCH v.fileDataList WHERE v IN :variants")
    List<ProductVariant> findVariantsWithFileData(@Param("variants") List<ProductVariant> variants);
}
