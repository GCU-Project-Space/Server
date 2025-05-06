package com.example.order_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Dto {
    public final boolean isSuccess;
    public final int code;
    public final String message;
}
