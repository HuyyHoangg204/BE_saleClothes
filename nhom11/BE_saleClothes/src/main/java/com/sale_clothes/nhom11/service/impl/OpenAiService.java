package com.sale_clothes.nhom11.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    @Autowired
    private  RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private  String openAiApiKey;



    public List<Float> createEmbedding(String input) {
        String url = "https://api.openai.com/v1/embeddings";

        Map<String, Object> body = Map.of(
                "input", input,
                "model", "text-embedding-ada-002"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(openAiApiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        var response = restTemplate.postForEntity(url, request, Map.class);

        Map<String, Object> responseBody = response.getBody();
        var data = (List<Map<String, Object>>) responseBody.get("data");
        List<Float> embedding = (List<Float>) data.get(0).get("embedding");
        return embedding;
    }

    public String chatCompletion(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";

        Map<String, Object> message = Map.of(
                "role", "user",
                "content", prompt
        );

        Map<String, Object> body = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(message)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(openAiApiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        var response = restTemplate.postForEntity(url, request, Map.class);

        Map<String, Object> responseBody = response.getBody();
        var choices = (List<Map<String, Object>>) responseBody.get("choices");
        Map<String, Object> firstChoice = choices.get(0);
        Map<String, Object> messageResponse  = (Map<String, Object>) firstChoice.get("message");
        String answer = (String) messageResponse .get("content");

        return answer;
    }

}
