package com.sale_clothes.nhom11.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeeklyAndMonthlyResponse {
    private String name;
    private double revenue;
}
