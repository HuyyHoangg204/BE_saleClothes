package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderStatusValidator {

    public void validateStatusTransition(OrderStatus current, OrderStatus next) {
        Set<OrderStatus> allowedNextStatuses = getAllowedNextStatuses(current);

        if (!allowedNextStatuses.contains(next)) {
            throw new IllegalArgumentException("Không thể chuyển trạng thái từ " + current + " sang " + next);
        }
    }

    private Set<OrderStatus> getAllowedNextStatuses(OrderStatus status) {
        return switch (status) {
            case PENDING -> Set.of(OrderStatus.PROCESSING, OrderStatus.CANCELLED);
            case PROCESSING -> Set.of(OrderStatus.SHIPPED, OrderStatus.CANCELLED);
            case SHIPPED -> Set.of(OrderStatus.DELIVERED, OrderStatus.RETURNED);
            case DELIVERED -> Set.of(OrderStatus.RETURNED);
            case RETURNED, CANCELLED -> Set.of(); // Không cho chuyển tiếp
        };
    }
}
