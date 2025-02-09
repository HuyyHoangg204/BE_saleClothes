package com.sale_clothes.nhom11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestCart {
    private String guestCartId;
    private List<int> productIds;
}
