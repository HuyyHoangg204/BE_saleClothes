package com.sale_clothes.nhom11.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KhuyenMaiDTO {
    Integer id;
    String code;
    String description;
    // Percentage, fixed_amount
    String discountType;
    Double discountValue;
    Double maxDiscountValue;
    Double minOrderAmount;
    LocalDate startDate;
    LocalDate endDate;
    Integer usageLimit;
    LocalDate createdAt;
    LocalDate updatedAt;
}
