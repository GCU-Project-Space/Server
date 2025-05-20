package gcu.web.paymentservice.platform.adapter.out.external.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "order-service", url = "http://34.127.7.212:8101")
public interface OrderFeignPort {

    /*
       - 오더 서비스에게 메세지 전송을 하는 포트를 정의한다.
     */

    @PostMapping("/api/v1/orders/{orderId}/cancel")
    String cancelOrder(@PathVariable("orderId") String orderId);

    @PostMapping("/api/v1/orders/{orderId}")
    String completeOrder(@PathVariable("orderId") String orderId);

}

