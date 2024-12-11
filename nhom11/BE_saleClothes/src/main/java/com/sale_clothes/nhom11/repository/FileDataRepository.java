package com.sale_clothes.nhom11.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sale_clothes.nhom11.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Integer> {
    Optional<FileData> findByName(String fileName);


    @Query("select fileData from FileData fileData WHERE fileData.productVariant.variant_id = :variant_id")
    List<FileData> findAllByVariantId(@Param("variant_id") Integer variant_id);

    @Modifying
    @Transactional
    @Query("delete from FileData fileData where fileData.name = :name and fileData.productVariant.variant_id = :variant_id")
    void deleteByNameAndSpMa(@Param("name") String name, @Param("variant_id") Integer variant_id);


    void deleteById(Long id);
}
