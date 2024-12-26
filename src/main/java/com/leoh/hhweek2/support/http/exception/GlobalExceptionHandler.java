package com.leoh.hhweek2.support.http.exception;

import com.leoh.hhweek2.domain.exception.LectureNotFoundException;
import com.leoh.hhweek2.support.http.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LectureNotFoundException.class)
    public ApiResponse<Void> lectureNotFoundException() {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "해당 특강을 찾을 수 없습니다.");
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> exception() {
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "알 수 없는 예외가 발생하였습니다.");
    }

}
