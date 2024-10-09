package com.sale_clothes.nhom11.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sale_clothes.nhom11.entity.DanhMuc;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Integer> {}
