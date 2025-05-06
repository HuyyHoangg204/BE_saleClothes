package com.sale_clothes.nhom11.repository;

import com.sale_clothes.nhom11.entity.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory,Long> {

    @Query("select o from OrderStatusHistory o where o.order.orderId = :orderId")
    List<OrderStatusHistory> findAllByOrderId(@Param("orderId") String orderId);
}
