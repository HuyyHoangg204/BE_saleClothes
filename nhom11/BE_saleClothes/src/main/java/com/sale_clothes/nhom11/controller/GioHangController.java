package com.sale_clothes.nhom11.controller;

import com.sale_clothes.nhom11.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.GioHangDTO;
import com.sale_clothes.nhom11.service.impl.GioHangServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class GioHangController {
    @Autowired
    private GioHangServiceImpl gioHangServiceImpl;

    @PostMapping("/giohangs")
    public ResponseEntity<GioHangDTO> createGioHangDTO(@RequestBody GioHangDTO gioHangDTO) {
        GioHangDTO saveGioHang = gioHangServiceImpl.createGioHang(gioHangDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveGioHang);
    }

    @PostMapping("/{guestCartId}/add")
    public String addToCart(@PathVariable String guestCartId,
                            @RequestParam String productId,
                            @RequestParam String size,
                            @RequestParam String color,
                            @RequestParam int quantity) {
        gioHangServiceImpl.addToCart(guestCartId,productId,size,color,quantity);
        return "Added " + quantity + " of product " + productId + " (Size: " + size + ", Color: " + color + ") to cart " + guestCartId;
    }

    @GetMapping("/getCart/{guestCartId}")
    public ApiResponse<Map<String, Integer>> getCart(@PathVariable String guestCartId) {
        Map<String, Integer> result = gioHangServiceImpl.getCart(guestCartId);
        return ApiResponse.<Map<String, Integer>>builder()
                .result(result)
                .build();
    }

    @DeleteMapping("/{guestCartId}/removeCart")
    public String removeFromCart(@PathVariable String guestCartId,
                                 @RequestParam String productId,
                                 @RequestParam String size,
                                 @RequestParam String color) {
        gioHangServiceImpl.removeFromCart(guestCartId,productId,size,color);
        return "Removed product " + productId + " (Size: " + size + ", Color: " + color + ") from cart " + guestCartId;
    }
}
