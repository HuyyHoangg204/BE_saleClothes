package com.sale_clothes.nhom11.service.impl;

import com.sale_clothes.nhom11.dto.DonDatHangDTO;
import com.sale_clothes.nhom11.dto.ProductEditOrderDTO;
import com.sale_clothes.nhom11.dto.ProductVariantDTO;
import com.sale_clothes.nhom11.dto.request.OrderEditRequest;
import com.sale_clothes.nhom11.dto.response.*;
import com.sale_clothes.nhom11.entity.*;
import com.sale_clothes.nhom11.enums.OrderStatus;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.mapper.AddressMapper;
import com.sale_clothes.nhom11.mapper.OrderDetailMapper;
import com.sale_clothes.nhom11.mapper.OrderStatusHIstoryMapper;
import com.sale_clothes.nhom11.mapper.ProductVariantMapper;
import com.sale_clothes.nhom11.repository.*;
import com.sale_clothes.nhom11.repository.OrderStatusHistoryRepository;
import com.sale_clothes.nhom11.service.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
    private SanPhamRepository sanPhamRepository;

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

    @Autowired
    private ProductVariantMapper productVariantMapper;

    @Autowired
    private OrderStatusValidator orderStatusValidator;

    private int size = 7; // Số bản ghi trong 1 trang
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
            orderDetailEntity.setSize(orderDetail.getSize());

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
                    .filter(h -> h.getStatus() == OrderStatus.PENDING)
                    .findFirst()
                    .ifPresent(h -> orderResponse.setOrderDate(h.getStatusDate()));

            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

    @Override
    public List<OrderManagerResponse> getAllOrderForManager(int page) {
       List<DonDatHang> donDatHangList = donDatHangRepository.findAll();
       List<OrderManagerResponse>  orderManagerResponses = new ArrayList<>();

       for(DonDatHang donDatHang : donDatHangList) {
           OrderStatusHistory orderStatusHistory = orderStatusHistoryRepository.findByOrderDateAndOrderId(donDatHang.getOrderId(), donDatHang.getCurrentStatus())
                   .orElseThrow(() -> new NotFoundException("Không tim thấy bản ghi lịch sử đơn hàng"));

           orderManagerResponses.add(OrderManagerResponse.builder()
                           .orderId(donDatHang.getOrderId())
                           .deliveryMethod(donDatHang.getDeliveryMethod())
                           .fullName(donDatHang.getAddress().getFullName())
                           .orderCode(donDatHang.getOrderCode())
                           .status(donDatHang.getCurrentStatus())
                           .totalAmount(donDatHang.getTotalAmount())
                           .paymentStatus(donDatHang.getPaymentStatus())
                           .orderDate(orderStatusHistory.getStatusDate())
                   .build());
       }

    return orderManagerResponses;
    }

    @Override
    public OrderEditResponse getOrderForEdit(String orderId) {
        DonDatHang donDatHang = donDatHangRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng với id: " + orderId));

        List<OrderStatusHistory> orderStatusHistories = orderStatusHistoryRepository.findAllByOrderId(donDatHang.getOrderId());

        LocalDateTime orderDate = orderStatusHistories.stream()
                .filter(h -> h.getStatus() == OrderStatus.PENDING) // Chỉ lấy orderDate có orderstatus = pending trong bảng orderHistory
                .map(OrderStatusHistory::getStatusDate)
                .findFirst()
                .orElse(null);

        // Lấy danh sách variant_id
        List<Integer> variantIds = donDatHang.getOrderDetails().stream()
                .map(od -> od.getProductVariant().getVariant_id())
                .collect(Collectors.toList());

        // Lấy map variantId -> SanPham
        List<SanPham> sanPhams = sanPhamRepository.findAllByVariantIdIn(variantIds);
        Map<Integer, SanPham> sanPhamMap = new HashMap<>();
        for (SanPham sanPham : sanPhams) {
            for (ProductVariant variant : sanPham.getProductVariants()) {
                sanPhamMap.put(variant.getVariant_id(), sanPham);
            }
        }

        // Tạo list productEditOrderDTO
        List<ProductEditOrderDTO> productEditOrderDTOS = donDatHang.getOrderDetails().stream()
                .map(od -> {
                    ProductVariant variant = od.getProductVariant(); // Lấy ra variant từ order detail
                    SanPham sanPham = sanPhamMap.get(variant.getVariant_id()); // Lấy ra sản phẩm thông qua key là variant_id

                    if (sanPham == null) {
                        throw new NotFoundException("Không tìm thấy sản phẩm với variantId: " + variant.getVariant_id());
                    }
                    double total = od.getQuantity() * sanPham.getBase_price();

                    ProductEditOrderDTO dto = new ProductEditOrderDTO();
                    dto.setProduct_id(sanPham.getProduct_id());
                    dto.setName(sanPham.getName());
                    dto.setColorName(variant.getColor().getColorName());
                    dto.setBase_price(sanPham.getBase_price());
                    dto.setQuantity(od.getQuantity());
                    dto.setTotal(total);
                    dto.setSize(od.getSize());
                    return dto;
                })
                .collect(Collectors.toList());

        return OrderEditResponse.builder()
                .orderId(donDatHang.getOrderId())
                .addressDTO(addressMapper.mapToAddressDTO(donDatHang.getAddress()))
                .status(donDatHang.getCurrentStatus())
                .totalAmount(donDatHang.getTotalAmount())
                .paymentStatus(donDatHang.getPaymentStatus())
                .deliveryMethod(donDatHang.getDeliveryMethod())
                .orderCode(donDatHang.getOrderCode())
                .orderDate(orderDate)
                .productEditOrderDTOS(productEditOrderDTOS)
                .build();
    }

    //duyệt đơn hàng
    @Transactional
    public String editStatusOrder(String orderId, OrderEditRequest orderEditRequest) {
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();

        DonDatHang donDatHang = donDatHangRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng với id:" + orderId));

        orderStatusValidator.validateStatusTransition(donDatHang.getCurrentStatus(), orderEditRequest.getOrderStatus());

        orderStatusHistory.setStatus(orderEditRequest.getOrderStatus());
        orderStatusHistory.setDescription(orderEditRequest.getDescription());
        orderStatusHistory.setStatusDate(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        orderStatusHistory.setOrder(donDatHang);

        donDatHang.setCurrentStatus(orderEditRequest.getOrderStatus());
        donDatHangRepository.save(donDatHang); //Update lại current status

        orderStatusHistoryRepository.save(orderStatusHistory);
        return "Cập nhật trạng thái đơn hàng thành công";
    }

// Lấy thông tin đơn hàng cho chức năng xem chi tiết đơn hàng
    public OrderDetailResponse getAllInformationOrder(String orderId) {
        DonDatHang donDatHang = donDatHangRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng!"));

        OrderStatusHistory orderStatusHistory = orderStatusHistoryRepository.findByOrderDateAndOrderId(donDatHang.getOrderId(), donDatHang.getCurrentStatus())
                .orElseThrow(() -> new NotFoundException("Không tim thấy bản ghi lịch sử đơn hàng"));

        List<OrderStatusHistory> orderStatusHistories = orderStatusHistoryRepository.findAllByOrderId(orderId);

        return OrderDetailResponse.builder()
                .orderId(donDatHang.getOrderId())
                .paymentStatus(donDatHang.getPaymentStatus())
                .totalAmount(donDatHang.getTotalAmount())
                .shippingFee(donDatHang.getShippingFee())
                .deliveryMethod(donDatHang.getDeliveryMethod())
                .paymentMethod(donDatHang.getPaymentMethod())
                .addressDTO(addressMapper.mapToAddressDTO(donDatHang.getAddress()))
                .orderDate(orderStatusHistory.getStatusDate())
                .deliveryMethod(donDatHang.getDeliveryMethod())
                .orderCode(donDatHang.getOrderCode())
                .orderStatusHistoryDTOS(orderStatusHIstoryMapper.mapToOrderStatusHistoryDTOs(orderStatusHistories))
                .build();
    }

    // Lọc đơn hàng thông qua status và date

    public List<OrderManagerResponse> filterOrderByStatusAndFromDate(int page, OrderStatus orderStatus, int days ) {
        List<DonDatHang> donDatHangList = donDatHangRepository.findAll();
        List<OrderManagerResponse>  orderManagerResponses = new ArrayList<>();

        for(DonDatHang donDatHang : donDatHangList) {
            OrderStatusHistory orderStatusHistory = orderStatusHistoryRepository.findByOrderDateAndOrderId(donDatHang.getOrderId(), donDatHang.getCurrentStatus())
                    .orElseThrow(() -> new NotFoundException("Không tim thấy bản ghi lịch sử đơn hàng"));

            orderManagerResponses.add(OrderManagerResponse.builder()
                    .orderId(donDatHang.getOrderId())
                    .deliveryMethod(donDatHang.getDeliveryMethod())
                    .fullName(donDatHang.getAddress().getFullName())
                    .orderCode(donDatHang.getOrderCode())
                    .status(donDatHang.getCurrentStatus())
                    .totalAmount(donDatHang.getTotalAmount())
                    .paymentStatus(donDatHang.getPaymentStatus())
                    .orderDate(orderStatusHistory.getStatusDate())
                    .build());
        }

        return orderManagerResponses;
    }


}
