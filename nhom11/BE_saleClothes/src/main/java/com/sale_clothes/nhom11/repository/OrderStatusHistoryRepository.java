package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.OrderStatusHistory;
import com.sale_clothes.nhom11.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory,Long> {

    @Query("select o from OrderStatusHistory o where o.order.orderId = :orderId")
    List<OrderStatusHistory> findAllByOrderId(@Param("orderId") String orderId);


    @Query("select o from OrderStatusHistory o where o.status = :status and o.order.orderId = :orderId")
    Optional<OrderStatusHistory> findByOrderDateAndOrderId(@Param("orderId") String orderId, @Param("status") OrderStatus orderStatus);
}
