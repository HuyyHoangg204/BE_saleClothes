package com.sale_clothes.nhom11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sale_clothes.nhom11.entity.DanhMucCon;

@Repository
public interface DanhMucConRepository extends JpaRepository<DanhMucCon, Integer> {
    @Query("SELECT dmc FROM DanhMucCon dmc WHERE dmc.dmMa.dmMa = :dmMa")
    List<DanhMucCon> getDanhMucConByDmMa(@Param("dmMa") Integer dmMa);
}
