package com.example.recruitment.service;

import com.example.recruitment.dto.order.OrderRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OrderClient {

    private final RestTemplate restTemplate = new RestTemplate();

    //직접 값 선언
    private final String serverUrl = "http://54.66.149.225:8100";
    private final String path = "/api/v1/orders";

    public Long createOrder(OrderRequestDto requestDto) {
        String fullUrl = serverUrl + path;

        ResponseEntity<Map> response = restTemplate.postForEntity(fullUrl, requestDto, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && responseBody.containsKey("data")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null && data.containsKey("orderId")) {
                    return Long.valueOf(data.get("orderId").toString());
                }
            }

            throw new RuntimeException("응답에 orderId 없음");
        }

        throw new RuntimeException("주문 생성 실패: " + response.getStatusCode());
    }
}
