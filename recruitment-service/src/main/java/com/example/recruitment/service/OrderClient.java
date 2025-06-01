package com.example.recruitment.service;

import com.example.recruitment.dto.order.OrderRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OrderClient {

    private final RestTemplate restTemplate = new RestTemplate();

    // 직접 값 선언
    private final String serverUrl = "http://54.66.149.225:8100";
    private final String path = "/api/v1/orders";

    public Long createOrder(OrderRequestDto requestDto) {
        String fullUrl = serverUrl + path;

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(fullUrl, requestDto, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseBody = response.getBody();

                if (responseBody == null || !responseBody.containsKey("data")) {
                    throw new RuntimeException("Order 서버 응답에 'data' 항목이 없음");
                }

                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                if (data == null || !data.containsKey("orderId")) {
                    throw new RuntimeException("Order 서버 응답 'data'에 'orderId'가 없음");
                }

                Object orderIdObj = data.get("orderId");
                try {
                    return Long.valueOf(orderIdObj.toString());
                } catch (NumberFormatException e) {
                    throw new RuntimeException("orderId를 Long으로 변환 실패: " + orderIdObj);
                }

            } else {
                throw new RuntimeException("Order 서버 응답 실패: " + response.getStatusCode());
            }
        } catch (Exception e) {
            // 서버 로깅 시스템 있다면 여기에 log.error(...) 사용 권장
            System.err.println("Order 서버 호출 중 예외 발생: " + e.getMessage());
            throw new RuntimeException("Order 서버 호출 실패", e);
        }
    }
}
