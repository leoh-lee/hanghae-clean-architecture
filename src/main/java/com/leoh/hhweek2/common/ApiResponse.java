package com.leoh.hhweek2.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private int code;
    private String message;

    private T result;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    public static <T> ApiResponse<T> ok(T result) {
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), result);
    }
}
