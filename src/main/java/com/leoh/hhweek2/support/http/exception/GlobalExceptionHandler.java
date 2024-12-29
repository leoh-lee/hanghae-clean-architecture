package com.leoh.hhweek2.support.http.exception;

import com.leoh.hhweek2.domain.exception.EnrollmentLimitExceededException;
import com.leoh.hhweek2.domain.exception.LectureNotFoundException;
import com.leoh.hhweek2.support.http.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LectureNotFoundException.class)
    public ApiResponse<Void> lectureNotFoundException(LectureNotFoundException e) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(EnrollmentLimitExceededException.class)
    public ApiResponse<Void> enrollmentLimitExceededException(EnrollmentLimitExceededException e) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> exception(Exception e) {
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
