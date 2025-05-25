package com.sale_clothes.nhom11.service.impl;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class QdrantService {

    private final RestTemplate restTemplate;
    private final String qdrantUrl = "https://35820397-f562-4908-8631-16d05e07e293.europe-west3-0.gcp.cloud.qdrant.io:6333";

    public QdrantService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void upsertPoint(String collectionName, Integer pointId, List<Float> vector, Map<String, Object> payload) {
        String url = qdrantUrl + "/collections/" + collectionName + "/points?wait=true";

        Map<String, Object> body = Map.of(
                "points", List.of(
                        Map.of(
                                "id", pointId,
                                "vector", vector,
                                "payload", payload
                        )
                )
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body);
        restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
    }

    public List<Map<String, Object>> search(String collectionName, List<Float> vector, int top) {
        String url = qdrantUrl + "/collections/" + collectionName + "/points/search";

        Map<String, Object> body = Map.of(
                "vector", vector,
                "top", top,
                "with_payload", true
        );


        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body);

        var response = restTemplate.postForEntity(url, request, Map.class);
        Map<String, Object> responseBody = response.getBody();

        // Lấy kết quả points
        return (List<Map<String, Object>>) responseBody.get("result");
    }
}
