package gcu.web.paymentservice.platform.application.out;

import gcu.web.paymentservice.platform.adapter.in.web.dto.request.ConfirmPaymentRequest;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface PaymentExternalPort {

    /*
        PG 결제를 위한 포트
     */

    /// 결제 검증 요청
    HttpResponse<String> requestConfirm(ConfirmPaymentRequest request) throws IOException, InterruptedException;

    /// 결제 취소 요청
    HttpResponse<String> requestPaymentCancel(String paymentKey, String cancelReason) throws IOException, InterruptedException;

}
