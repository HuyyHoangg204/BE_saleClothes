package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    Optional<Image> findByName(String fileName);

}
