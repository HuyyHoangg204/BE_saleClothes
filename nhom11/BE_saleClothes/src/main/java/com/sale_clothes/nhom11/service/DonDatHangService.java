package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.DonDatHangDTO;
import com.sale_clothes.nhom11.dto.response.OrderResponse;

import java.util.List;

public interface DonDatHangService {
String createOrder(DonDatHangDTO dto);
List<OrderResponse> getAllOrderByUsername(String username);

}
