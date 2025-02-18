package com.sale_clothes.nhom11.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sale_clothes.nhom11.dto.GioHangDTO;
import com.sale_clothes.nhom11.dto.kafkaEvent.CartEvent;
import com.sale_clothes.nhom11.entity.GioHang;
import com.sale_clothes.nhom11.exception.NotFoundException;
import com.sale_clothes.nhom11.repository.ColorRepository;
import com.sale_clothes.nhom11.repository.GioHangRepository;
import com.sale_clothes.nhom11.repository.KhachHangRepository;
import com.sale_clothes.nhom11.service.GioHangService;

@Service
public class GioHangServiceImpl extends BaseRedisServiceImpl<String, String, Integer> implements GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private ColorRepository colorRepository;

    private final KafkaTemplate<String, CartEvent> kafkaTemplate;

    public GioHangServiceImpl(
            RedisTemplate<String, Integer> redisTemplate,
            HashOperations<String, String, Integer> hashOperations,
            KafkaTemplate<String, CartEvent> kafkaTemplate) {
        super(redisTemplate, hashOperations);
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public GioHangDTO createGioHang(GioHangDTO gioHangDTO) {
        GioHangDTO gioHangDTO1 = new GioHangDTO();
        return gioHangDTO1;
    }

    @Override
    public List<GioHangDTO> getAllGioHang() {
        return null;
    }

    // Add to cart before login: store in redis
    public void addToCart(String guestCartId, String productId, String size, String color, int quantity) {
        String cartKey = productId + "_" + size + "_" + color; // Key theo format "SP001_L_Red"
        Integer currentQuantity = (Integer) hashGet(guestCartId, cartKey);
        if (currentQuantity == null) {
            currentQuantity = 0;
        }
        hashSet(guestCartId, cartKey, currentQuantity + quantity);
    }

    // Add to cart after login: store in mysql
    public void addToCartAfterLogin(String username, int productId, String size, int colorId, int quantity) {

        Optional<GioHang> existingCartItem =
                gioHangRepository.findByUsernameAndProductIdAndSizeAndColorId(username, productId, size, colorId);

        if (existingCartItem.isPresent()) {
            GioHang gioHang = existingCartItem.get();
            gioHang.setQuantity(gioHang.getQuantity() + quantity); // Cộng dồn số lượng
            gioHangRepository.save(gioHang);
        } else {
            GioHang gioHang = new GioHang();
            gioHang.setUsername(username);
            gioHang.setProductId(productId);
            gioHang.setSize(size);
            gioHang.setColorId(colorId);
            gioHang.setQuantity(quantity);
            gioHangRepository.save(gioHang); // Thêm mới nếu chưa tồn tại
        }
    }
    // Then login: sync cart redis -> mysql
    @Transactional
    public void syncCartAfterLogin(String guestCartId, String username) {
        Map<String, Integer> guestCart = getField(guestCartId);
        if (!guestCart.isEmpty()) {
            for (Map.Entry<String, Integer> entry : guestCart.entrySet()) {
                GioHang gioHang = new GioHang();
                String productKey = entry.getKey();
                String[] data = productKey.split("_");
                Optional<GioHang> gioHang1 = gioHangRepository.findByUsernameAndProductIdAndSizeAndColorId(
                        username, Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]));
                if (gioHang1.isPresent()) {
                    GioHang gioHang2 = gioHang1.get();
                    int quantity = gioHang2.getQuantity() + entry.getValue();

                    gioHang2.setQuantity(quantity);
                    gioHangRepository.save(gioHang2);
                } else {
                    int quantity = entry.getValue();
                    gioHang.setUsername(username);
                    gioHang.setProductId(Integer.parseInt(data[0]));
                    gioHang.setSize(data[1]);
                    gioHang.setQuantity(quantity);
                    gioHang.setColorId(Integer.parseInt(data[2]));
                    gioHangRepository.save(gioHang);
                }
            }

            delete(guestCartId);
        }
    }

    // Lấy giỏ hàng của khách before login
    public Map<String, Integer> getCart(String guestCartId) {
        return getField(guestCartId);
    }

    // Get Product from cart after login
    public Map<String, Integer> getCartAfterLogin(String username) {
        Map<String, Integer> products = new HashMap<>();
        List<GioHang> gioHang = gioHangRepository.findAllByUsername(username);

        for (GioHang gioHang1 : gioHang) {
            int productId = gioHang1.getProductId();
            String size = gioHang1.getSize();
            int colorId = gioHang1.getColorId();
            int quantity = gioHang1.getQuantity();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(productId).append("_").append(size).append("_").append(colorId);
            products.put(stringBuilder.toString(), quantity);
        }
        return products;
    }

    // Xóa một sản phẩm khỏi giỏ hàng (before login)
    public void removeFromCart(String guestCartId, String productId, String size, String color) {
        String cartKey = productId + "_" + size + "_" + color;

        Integer quantity = (Integer) hashGet(guestCartId, cartKey);

        if (quantity != null) {
            if (quantity > 1) {
                hashSet(guestCartId, cartKey, quantity - 1);
            } else {
                delete(guestCartId, cartKey);
            }
        }
    }

    // Xóa một sản phẩm khỏi giỏ hàng (After login)
    public void removeFromCartAfterLogin(String username, int productId, String size, int colorId) {
        Optional<GioHang> gioHang =
                gioHangRepository.findByUsernameAndProductIdAndSizeAndColorId(username, productId, size, colorId);
        if (gioHang.isPresent()) {
            GioHang gioHang1 = gioHang.get();
            if (gioHang1.getQuantity() > 1) {
                gioHang1.setQuantity(gioHang1.getQuantity() - 1);
                gioHangRepository.save(gioHang1);
            } else {
                gioHangRepository.delete(gioHang1);
            }
        } else throw new NotFoundException("Product not found in cart");
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
