package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.DonDatHangDTO;
import com.sale_clothes.nhom11.dto.response.OrderResponse;
import com.sale_clothes.nhom11.entity.*;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.mapper.AddressMapper;
import com.sale_clothes.nhom11.mapper.OrderDetailMapper;
import com.sale_clothes.nhom11.mapper.OrderStatusHIstoryMapper;
import com.sale_clothes.nhom11.repository.*;
import com.sale_clothes.nhom11.repository.OrderStatusHistoryRepository;
import com.sale_clothes.nhom11.service.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class DonDatHangServiceImpl implements DonDatHangService {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DonDatHangRepository donDatHangRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private OrderStatusHistoryRepository orderStatusHistoryRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderStatusHIstoryMapper orderStatusHIstoryMapper;
    @Override
    @Transactional
    public String createOrder(DonDatHangDTO dto) {
        List<OrderDetail> orderDetails = dto.getOrderDetails();

        DonDatHang donDatHang = new DonDatHang();
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();

        System.out.println(donDatHang.getOrderId());

        KhachHang khachHang = khachHangRepository.findById(dto.getUsername())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy username:" + dto.getUsername()));
        donDatHang.setKhachHang(khachHang);

        // Set status order
        orderStatusHistory.setStatusDate(LocalDateTime.now());
        orderStatusHistory.setDescription("Đặt hàng thành công");
        orderStatusHistory.setStatus(OrderStatus.PENDING);
        orderStatusHistory.setOrder(donDatHang);
        orderStatusHistoryRepository.save(orderStatusHistory);

        donDatHang.setDeliveryMethod(dto.getDeliveryMethod());
        donDatHang.setPaymentMethod(dto.getPaymentMethod());
        donDatHang.setShippingFee(dto.getShippingFee());
        donDatHang.setTotalAmount(dto.getTotalAmount());
        donDatHang.setPaymentStatus(dto.getPaymentStatus());
        donDatHang.setCurrentStatus(OrderStatus.PENDING);

        Address address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy địa chỉ này!"));
        donDatHang.setAddress(address);

        donDatHangRepository.save(donDatHang);


        for (OrderDetail orderDetail : orderDetails) {
            ProductVariant productVariant = productVariantRepository.findByIdForUpdate(
                            orderDetail.getProductVariant().getVariant_id())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm!"));

            if (productVariant.getStockQuantity() < orderDetail.getQuantity()) {
                throw new RuntimeException("Sản phẩm đã hết hàng hoặc không đủ số lượng");
            }

            productVariant.setStockQuantity(productVariant.getStockQuantity() - orderDetail.getQuantity());
            productVariantRepository.save(productVariant);

            OrderDetail orderDetailEntity = new OrderDetail();
            orderDetailEntity.setOrder(donDatHang);
            orderDetailEntity.setQuantity(orderDetail.getQuantity());
            orderDetailEntity.setProductVariant(productVariant);

            orderDetailRepository.save(orderDetailEntity);
        }

        //Xóa tất cả sản phẩm khỏi giỏ hàng sau kkhi đặt hàng thành công
        gioHangRepository.deleteByUsername(dto.getUsername());

        return "Đặt hàng thành công";
    }

    @Override
    public List<OrderResponse> getAllOrderByUsername(String username) {
        List<OrderResponse> orderResponseList = new ArrayList<>();

        List<DonDatHang> donDatHangList = donDatHangRepository.findAllByUsername(username);

        for(DonDatHang donDatHang : donDatHangList) {
            OrderResponse orderResponse = new OrderResponse();
            List<OrderStatusHistory> orderStatusHistories = orderStatusHistoryRepository.findAllByOrderId(donDatHang.getOrderId());


            orderResponse.setTotalAmount(donDatHang.getTotalAmount());
            orderResponse.setOrderDetails(orderDetailMapper.mapToOrderDetailDTOS(donDatHang.getOrderDetails()));
            orderResponse.setStatus(donDatHang.getCurrentStatus());
            orderResponse.setAddress(addressMapper.mapToAddressDTO(donDatHang.getAddress()));
            orderResponse.setOrderStatusHistories(orderStatusHIstoryMapper.mapToOrderStatusHistoryDTOs(orderStatusHistories));
            orderResponse.setOrderId(donDatHang.getOrderId());
            orderResponse.setOrderCode(donDatHang.getOrderCode());
            orderResponse.setPaymentMethod(donDatHang.getPaymentMethod());

            orderStatusHistories.stream()
                    .filter(h -> h.getStatus() == donDatHang.getCurrentStatus())
                    .findFirst()
                    .ifPresent(h -> orderResponse.setOrderDate(h.getStatusDate()));

            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

}
