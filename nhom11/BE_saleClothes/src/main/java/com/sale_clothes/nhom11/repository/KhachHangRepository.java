package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.dto.KhachHangDTO;
import com.sale_clothes.nhom11.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,String> {

    boolean existsByKhUserName(String userName);
    boolean existsByKhEmail(String email);

}
