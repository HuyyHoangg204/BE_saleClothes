package com.sale_clothes.nhom11.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sale_clothes.nhom11.dto.GioHangDTO;
import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.service.impl.GioHangServiceImpl;

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
    public String addToCart(
            @PathVariable String guestCartId,
            @RequestParam String productId,
            @RequestParam String size,
            @RequestParam String color,
            @RequestParam int quantity) {
        gioHangServiceImpl.addToCart(guestCartId, productId, size, color, quantity);
        return "Added " + quantity + " of product " + productId + " (Size: " + size + ", Color: " + color + ") to cart "
                + guestCartId;
    }

    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    @PostMapping("/addToCartAfterLogin")
    public ApiResponse<String> addToCartAfterLogin(
            @RequestParam String username,
            @RequestParam int productID,
            @RequestParam String size,
            @RequestParam int colorId,
            @RequestParam int quantity) {
        String message;
        try {
            gioHangServiceImpl.addToCartAfterLogin(username, productID, size, colorId, quantity);
            message = "Thêm vào giỏ hàng thành công!!";
        } catch (Exception e) {
            message = "Thêm vào giò hàng thất bại: " + e;
        }
        return ApiResponse.<String>builder().message(message).build();
    }

    // Get cart before login

    @GetMapping("/getCart/{guestCartId}")
    public ApiResponse<Map<String, Integer>> getCart(@PathVariable String guestCartId) {
        Map<String, Integer> result = gioHangServiceImpl.getCart(guestCartId);
        return ApiResponse.<Map<String, Integer>>builder().result(result).build();
    }

    // Get cart after login
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    @GetMapping("/getCartAfterLogin/{username}")
    public ApiResponse<Map<String, Integer>> getCartAfterLogin(@PathVariable String username) {
        Map<String, Integer> result = gioHangServiceImpl.getCartAfterLogin(username);
        return ApiResponse.<Map<String, Integer>>builder().result(result).build();
    }

    // Delete product before login
    @DeleteMapping("/{guestCartId}/removeCart")
    public String removeFromCart(
            @PathVariable String guestCartId,
            @RequestParam String productId,
            @RequestParam String size,
            @RequestParam String color) {
        gioHangServiceImpl.removeFromCart(guestCartId, productId, size, color);
        return "Removed product " + productId + " (Size: " + size + ", Color: " + color + ") from cart " + guestCartId;
    }

    // Delete product after login
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    @DeleteMapping("/removeCartAfterLogin")
    public String removeFromCartAfterLogin(
            @RequestParam String username,
            @RequestParam int productId,
            @RequestParam String size,
            @RequestParam int colorId) {
        gioHangServiceImpl.removeFromCartAfterLogin(username, productId, size, colorId);
        return "Removed product " + productId + " (Size: " + size + ", Color: " + colorId + ") from cart " + username;
    }
}
