package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DonDatHangRepository extends JpaRepository<DonDatHang, UUID> {
}
