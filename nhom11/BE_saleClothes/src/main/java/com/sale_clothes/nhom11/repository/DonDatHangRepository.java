package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DonDatHangRepository extends JpaRepository<DonDatHang, UUID> {

    @Query("SELECT d from DonDatHang d where d.khachHang.khUserName = :username")
    List<DonDatHang> findAllByUsername(@Param("username") String username);
}
