package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("Select ad from Address ad where ad.khachHang.khUserName=:username")
    List<Address> findAllByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("delete from Address ad where ad.khachHang.khUserName=:username and ad.id=:id")
    void deleteByUsernameAndId(@Param("username") String username, @Param("id") Long id);
}
