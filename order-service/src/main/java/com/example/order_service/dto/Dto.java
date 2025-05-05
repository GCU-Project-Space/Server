package com.example.order_service.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Dto {
    public final boolean isSuccess;
    public final int code;
    public final String message;
}
