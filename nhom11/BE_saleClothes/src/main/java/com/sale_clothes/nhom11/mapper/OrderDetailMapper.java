package com.sale_clothes.nhom11.mapper;


import com.sale_clothes.nhom11.dto.OrderDetailDTO;
import com.sale_clothes.nhom11.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(source = "orderId", target = "order.orderId")  // Ánh xạ orderId về Order
    @Mapping(source = "productVariantId", target = "productVariant.variant_id")  // Ánh xạ productVariantId về ProductVariant
    OrderDetail mapToOrderDetail(OrderDetailDTO orderDetailDTO);

    @Mapping(source = "order.orderId", target = "orderId")  // Ánh xạ order thành orderId
    @Mapping(source = "productVariant.variant_id", target = "productVariantId")  // Ánh xạ productVariant thành productVariantId
    OrderDetailDTO mapToOrderDetailDTO(OrderDetail orderDetail);

    List<OrderDetailDTO> mapToOrderDetailDTOS(List<OrderDetail> orderDetails);


}
