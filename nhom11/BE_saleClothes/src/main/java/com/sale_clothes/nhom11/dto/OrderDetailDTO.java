package com.sale_clothes.nhom11.dto;

import com.sale_clothes.nhom11.entity.DonDatHang;
import com.sale_clothes.nhom11.entity.ProductVariant;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderDetailDTO {
    Long id;

    String orderId;

    Integer productVariantId;

    Integer quantity;  // Số lượng sản phẩm trong đơn hàng
}
