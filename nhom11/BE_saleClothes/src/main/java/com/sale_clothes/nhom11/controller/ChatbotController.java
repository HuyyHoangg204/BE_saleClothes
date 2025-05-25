package com.sale_clothes.nhom11.controller;


import com.sale_clothes.nhom11.dto.response.ApiResponse;
import com.sale_clothes.nhom11.entity.ProductVariant;
import com.sale_clothes.nhom11.repository.ProductVariantRepository;
import com.sale_clothes.nhom11.service.impl.OpenAiService;
import com.sale_clothes.nhom11.service.impl.QdrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/chatbot")
public class ChatbotController {

    @Autowired
    private OpenAiService openAIService;

    @Autowired
    private QdrantService qdrantService;


    @Autowired
    private ProductVariantRepository productVariantRepository;

    @PostMapping("/ask")
    public ResponseEntity<?> ask(@RequestBody Map<String, String> request) {
        String question = request.get("question");

        // Tạo embedding cho câu hỏi
        List<Float> questionEmbedding = openAIService.createEmbedding(question);

        // Tìm kiếm trong Qdrant
        var results = qdrantService.search("products_collection", questionEmbedding, 10);



        if (results.isEmpty()) {
            return ResponseEntity.ok(Map.of("answer", "Xin lỗi, tôi không tìm thấy thông tin phù hợp."));
        }

        // Chuẩn bị context từ các kết quả tìm được
        StringBuilder context = new StringBuilder();
        for (var point : results) {
            Map<String, Object> payload = (Map<String, Object>) point.get("payload");




            String name = (String) payload.get("name");
            String description = (String) payload.get("description");
            String color = (String) payload.get("color");
            Object sizeObj = payload.get("size");
            String size = "";

            if (sizeObj instanceof List) {
                size = String.join(", ", ((List<?>) sizeObj).stream().map(Object::toString).toList());
            } else if (sizeObj != null) {
                size = sizeObj.toString();
            }
            Integer stock = (Integer) payload.get("stock");
            Double price = (Double) payload.get("price");
            Double originalPrice = (Double) payload.get("original_price");
            Integer discountedPrice = (Integer) payload.get("discount");
            String material = (String) payload.get("material");

            context.append("Sản phẩm: ").append(name).append("\n");
            context.append("Mô tả: ").append(description).append("\n");
            context.append("Chất liệu: ").append(material).append("\n");
            context.append("Phiên bản:\n");
            context.append("  - Màu: ").append(color).append("\n");
            context.append("  - Size: ").append(size).append("\n");
            context.append("  - Số lượng còn: ").append(stock).append("\n");
            context.append("Khuyến mãi: Giảm ").append(discountedPrice).append("%\n");
            context.append(String.format("Giá hiện tại: %.0f₫ (Giá gốc: %.0f₫)", price, originalPrice));
            context.append("\n\n");

        }

        // Tạo prompt cho GPT
        String prompt = String.format(
                "Bạn là trợ lý ảo của cửa hàng. Hãy trả lời câu hỏi sau dựa trên thông tin sản phẩm:\n" +
                        "Câu hỏi: %s\n\n" +
                        "Thông tin sản phẩm liên quan:\n%s\n" +
                        "Hãy trả lời một cách thân thiện, chính xác và hữu ích.",
                question,
                context.toString()
        );

        String answer = openAIService.chatCompletion(prompt);
        return ResponseEntity.ok(Map.of("answer", answer));
    }



}
