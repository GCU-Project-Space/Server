package gcu.web.paymentservice.platform.adapter.out.external.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "order-service", url = "http://54.66.149.225:8100")
public interface OrderFeignPort {

    /*
       - 오더 서비스에게 메세지 전송을 하는 포트를 정의한다.
     */

    @PostMapping("/api/v1/orders/{orderId}/cancel")
    String cancelOrder(@PathVariable("orderId") String orderId);

    @PutMapping("/api/v1/orders/{orderId}/submit")
    String completeOrder(@PathVariable("orderId") Long orderId);


}

