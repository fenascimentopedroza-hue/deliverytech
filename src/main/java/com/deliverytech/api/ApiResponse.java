package com.deliverytech.api;

import java.time.Instant;

public record ApiResponse<T>(boolean success, T data, String message, Instant timestamp) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, Instant.now());
    }
    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(true, data, "Created", Instant.now());
    }
    public static ApiResponse<Object> error(String code, String message) {
        return new ApiResponse<>(false, null, code + ": " + message, Instant.now());
    }
}
