package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String fileName);
    @Query("select fileData from FileData fileData WHERE fileData.sanPham.spMa = :spMa")
    List<FileData> findAllBySpMa(@Param("spMa") Integer spMa);

    @Modifying
    @Transactional
    @Query("delete from FileData fileData where fileData.name = :name and fileData.sanPham.spMa = :spMa")
    void deleteByNameAndSpMa(@Param("name") String name,@Param("spMa") Integer spma);



    void deleteById(Long id);

}
