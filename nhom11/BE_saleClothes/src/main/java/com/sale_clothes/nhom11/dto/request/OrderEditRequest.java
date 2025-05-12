package com.sale_clothes.nhom11.dto.request;

import com.sale_clothes.nhom11.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEditRequest {
    private OrderStatus orderStatus;
    private String description;
}
