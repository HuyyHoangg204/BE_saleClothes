package com.sale_clothes.nhom11.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sale_clothes.nhom11.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    boolean existsByColorCode(String colorCode);
}
