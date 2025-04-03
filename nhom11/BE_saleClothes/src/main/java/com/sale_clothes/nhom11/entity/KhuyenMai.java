package com.sale_clothes.nhom11.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "khuyen_mai")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhuyenMai {
    private static String uniqueString;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String code = uniqueString;
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
    static{
        uniqueString =  UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }
}
