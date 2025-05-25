package com.sale_clothes.nhom11.service.impl;


import com.sale_clothes.nhom11.entity.ProductVariant;
import com.sale_clothes.nhom11.entity.SanPham;
import com.sale_clothes.nhom11.repository.ProductVariantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ChatbotTrainingService {
    @Autowired
    private OpenAiService openAiService;


    @Autowired
    private QdrantService qdrantService;


    @Autowired
    private ProductVariantRepository productVariantRepository;


    @Transactional
    public void trainWithProductData() {
        List<ProductVariant> productVariants = productVariantRepository.findAll();

        for (ProductVariant productVariant : productVariants) {
            try {
                SanPham product = productVariant.getProduct(); // Lấy thông tin sản phẩm cha

                Map<String, Object> payload = new HashMap<>();
                payload.put("variant_id", productVariant.getVariant_id());
                payload.put("product_id", product.getProduct_id());
                payload.put("name", product.getName());
                payload.put("stock", productVariant.getStockQuantity());
                payload.put("color", productVariant.getColor().getColorName());
                payload.put("size", productVariant.getSize()); // Thêm size nếu có
                payload.put("price", product.getBase_price());
                payload.put("original_price", calculateOriginalPrice(product)); // Giá sau giảm
                payload.put("description", product.getDescription());
                payload.put("discount", product.getDiscount_percentage());
                payload.put("material", product.getMaterial());


                // Tạo embedding và lưu vào Qdrant
                String productInfo = buildProductInfoString(productVariant);
                List<Float> embedding = openAiService.createEmbedding(productInfo);

                // Nếu pointId là số nguyên:
                Integer pointId = productVariant.getVariant_id();  // assuming getVariant_id() trả Integer
                qdrantService.upsertPoint(
                        "products_collection",
                        pointId,   // truyền kiểu Integer hoặc Long, không phải String "72"
                        embedding,
                        payload
                );

            } catch (Exception e) {
                log.error("Error processing variant {}: {}", productVariant.getVariant_id(), e.getMessage());
            }
        }
    }


    private String buildProductInfoString(ProductVariant productVariant) {
        SanPham product = productVariant.getProduct();
        return String.format("%s %s %s. %s. Màu: %s. Giá: %.2f (giảm %d%%). Chất liệu: %s",
                product.getName(),
                productVariant.getColor().getColorName(),
                productVariant.getSize() != null ? productVariant.getSize() : "",
                product.getDescription(),
                productVariant.getColor().getColorName(),
                calculateOriginalPrice(product),
                product.getDiscount_percentage(),
                product.getMaterial()
        );
    }

    private double calculateOriginalPrice(SanPham product) {
        double discountedPrice = product.getBase_price();  // giá sau giảm
        double discountPercent = product.getDiscount_percentage(); // ví dụ 30 (30%)
        if (discountPercent >= 100) {
            // Tránh chia cho 0 hoặc âm
            return discountedPrice; // hoặc xử lý lỗi tùy bạn
        }
        return discountedPrice / (1 - discountPercent / 100);
    }

}
