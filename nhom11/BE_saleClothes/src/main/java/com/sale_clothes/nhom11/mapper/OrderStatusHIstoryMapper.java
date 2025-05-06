package com.sale_clothes.nhom11.mapper;

import com.sale_clothes.nhom11.dto.OrderStatusHistoryDTO;
import com.sale_clothes.nhom11.entity.OrderStatusHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusHIstoryMapper {

    @Mapping(source = "orderId", target = "order.orderId")  // Ánh xạ orderId về Order
    OrderStatusHistory mapToOrderStatusHistory(OrderStatusHistoryDTO orderStatusHistoryDTO);

    @Mapping(source = "order.orderId", target = "orderId")  // Ánh xạ orderId về Order
    OrderStatusHistoryDTO mapToOrderStatusHistoryDTO(OrderStatusHistory orderStatusHistory);

    List<OrderStatusHistoryDTO> mapToOrderStatusHistoryDTOs(List<OrderStatusHistory> orderStatusHistories);
}
