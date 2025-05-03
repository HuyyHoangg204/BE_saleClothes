package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.DonDatHangDTO;
import com.sale_clothes.nhom11.entity.*;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.repository.*;
import com.sale_clothes.nhom11.service.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Override
    @Transactional
    public String createOrder(DonDatHangDTO dto) {
        List<OrderDetail> orderDetails = dto.getOrderDetails();

        DonDatHang donDatHang = new DonDatHang();

        System.out.println(donDatHang.getOrderId());

        KhachHang khachHang = khachHangRepository.findById(dto.getUsername())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy username:" + dto.getUsername()));
        donDatHang.setKhachHang(khachHang);

        donDatHang.setStatus(OrderStatus.PENDING);
        donDatHang.setDeliveryMethod(dto.getDeliveryMethod());
        donDatHang.setPaymentMethod(dto.getPaymentMethod());
        donDatHang.setShippingFee(dto.getShippingFee());
        donDatHang.setTotalAmount(dto.getTotalAmount());
        donDatHang.setPaymentStatus(dto.getPaymentStatus());

        Address address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy địa chỉ này!"));
        donDatHang.setAddress(address);
        donDatHang.setOrderDate(LocalDateTime.now());

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

}
