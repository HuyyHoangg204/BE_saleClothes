package com.sale_clothes.nhom11.controller;


import com.sale_clothes.nhom11.service.impl.ChatbotTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/chatbot")
public class ChatbotTrainingController {

    @Autowired
    private ChatbotTrainingService chatbotTrainingService;

    @PostMapping("/retrain")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> retrainProducts() {
        try {
            chatbotTrainingService.trainWithProductData();
            return ResponseEntity.ok("Training completed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Training failed: " + e.getMessage());
        }
    }
}
