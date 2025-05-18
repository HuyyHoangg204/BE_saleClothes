package com.sale_clothes.nhom11.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FullDailyResponse {
    private String name;
    private double revenue;
    private LocalDate date;
}
