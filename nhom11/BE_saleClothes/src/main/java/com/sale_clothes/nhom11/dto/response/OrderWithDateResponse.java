package com.sale_clothes.nhom11.dto.response;


import com.sale_clothes.nhom11.entity.DonDatHang;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderWithDateResponse {
    private DonDatHang order;
    private LocalDateTime statusDate;
}
