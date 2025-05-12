package com.sale_clothes.nhom11.service;

import com.sale_clothes.nhom11.dto.DonDatHangDTO;
import com.sale_clothes.nhom11.dto.response.OrderEditResponse;
import com.sale_clothes.nhom11.dto.response.OrderManagerResponse;
import com.sale_clothes.nhom11.dto.response.OrderResponse;
import com.sale_clothes.nhom11.enums.OrderStatus;

import java.util.List;

public interface DonDatHangService {
String createOrder(DonDatHangDTO dto);
List<OrderResponse> getAllOrderByUsername(String username);

List<OrderManagerResponse> getAllOrderForManager(int page);

OrderEditResponse getOrderForEdit(String orderId);

}
