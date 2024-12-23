package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Integer> {
    boolean existsByColorCode(String colorCode);
}
