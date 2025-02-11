package com.sale_clothes.nhom11.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sale_clothes.nhom11.dto.GioHangDTO;
import com.sale_clothes.nhom11.entity.GioHang;
import com.sale_clothes.nhom11.mapper.GioHangMapper;
import com.sale_clothes.nhom11.repository.GioHangRepository;
import com.sale_clothes.nhom11.service.GioHangService;

@Service
public class GioHangServiceImpl extends BaseRedisServiceImpl<String,String, Integer> implements GioHangService  {
    @Autowired
    private GioHangRepository gioHangRepository;



    public GioHangServiceImpl(RedisTemplate<String, Integer> redisTemplate, HashOperations<String, String, Integer> hashOperations) {
        super(redisTemplate, hashOperations);
    }

    @Override
    @Transactional
    public GioHangDTO createGioHang(GioHangDTO gioHangDTO) {
        GioHang gioHang = GioHangMapper.mapToGioHang(gioHangDTO);
        GioHang savedGioHang = gioHangRepository.save(gioHang);
        return GioHangMapper.mapToGioHangDTO(savedGioHang);
    }

    @Override
    public List<GioHangDTO> getAllGioHang() {
        return null;
    }

    public void addToCart(String guestCartId, String productId, String size, String color, int quantity) {
        String cartKey = productId + "_" + size + "_" + color; // Key theo format "SP001_L_Red"
        Integer currentQuantity = (Integer) hashGet(guestCartId, cartKey);
        if (currentQuantity == null) {
            currentQuantity = 0;
        }
        hashSet(guestCartId, cartKey, currentQuantity + quantity);


    }
    // Lấy giỏ hàng của khách
    public Map<String, Integer> getCart(String guestCartId) {
        return getField(guestCartId);
    }

    // Xóa một sản phẩm khỏi giỏ hàng
    public void removeFromCart(String guestCartId, String productId, String size, String color) {
        String cartKey = productId + "_" + size + "_" + color;

        Integer quantity = (Integer) hashGet(guestCartId, cartKey);

        if(quantity != null) {
            if(quantity > 1) {
                hashSet(guestCartId,cartKey,quantity - 1);
            } else {
                delete(guestCartId,cartKey);
            }
        }
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart(String guestCartId) {
        delete(guestCartId);
    }

    @Override
    public GioHangDTO getGioHangId(Integer id) {
        return null;
    }

    @Override
    public GioHangDTO updateGioHang(Integer id, GioHangDTO gioHangDTO) {
        return null;
    }

    @Override
    public void deleteGioHang(GioHangDTO gioHangDTO) {}
}
