package com.sale_clothes.nhom11.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestCart {
    private String guestCartId;
    private List<Integer> productIds;
}
