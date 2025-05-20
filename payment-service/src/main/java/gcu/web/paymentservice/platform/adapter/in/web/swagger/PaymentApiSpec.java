package gcu.web.paymentservice.platform.adapter.in.web.swagger;

import gcu.web.paymentservice.common.response.ApiResponse;
import gcu.web.paymentservice.platform.adapter.in.web.dto.request.CancelPaymentRequest;
import gcu.web.paymentservice.platform.adapter.in.web.dto.request.ConfirmPaymentRequest;
import gcu.web.paymentservice.platform.adapter.in.web.dto.response.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "결제 서버 API", description = "결제 승인 및 취소를 하는 API 입니다.")
public interface PaymentApiSpec {

    @Operation(
            summary = "결제 요청 API",
            description = "리액트의 코드를 바탕으로 결제 요청을 진행합니다."
    )
    ApiResponse<PaymentResponse> createPayment(
            @RequestBody ConfirmPaymentRequest request
    ) throws Exception;


    @Operation(
            summary = "결제 취소 API",
            description = "결제취소 요청을 받으면 토스에게 결제 취소를 진행합니다."
    )
    ApiResponse<String> cancelPayment(
            @RequestBody CancelPaymentRequest request) throws Exception;
}
