package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.dto.response.OrderWithDateResponse;
import com.sale_clothes.nhom11.entity.DonDatHang;
import com.sale_clothes.nhom11.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DonDatHangRepository extends JpaRepository<DonDatHang, UUID> {

    @Query("SELECT d from DonDatHang d where d.khachHang.khUserName = :username")
    List<DonDatHang> findAllByUsername(@Param("username") String username);



    @Query("SELECT new com.sale_clothes.nhom11.dto.response.OrderWithDateResponse(o.order, o.statusDate) " +
            "FROM OrderStatusHistory o " +
            "WHERE o.status = :status AND o.statusDate >= :fromDate")
    Page<OrderWithDateResponse> findOrdersByStatusAndDate(Pageable pageable,
                                                          @Param("status") OrderStatus orderStatus,
                                                          @Param("fromDate") LocalDateTime fromDate);

    @Query("SELECT new com.sale_clothes.nhom11.dto.response.OrderWithDateResponse(o.order, o.statusDate) " +
            "FROM OrderStatusHistory o " +
            "WHERE o.statusDate >= :fromDate")
    Page<OrderWithDateResponse> findOrdersByDateOnLy(Pageable pageable,
                                                          @Param("fromDate") LocalDateTime fromDate);

    @Query("select o from DonDatHang o where o.orderId = :orderId")
    Optional<DonDatHang> findById(@Param("orderId") String id);
}
