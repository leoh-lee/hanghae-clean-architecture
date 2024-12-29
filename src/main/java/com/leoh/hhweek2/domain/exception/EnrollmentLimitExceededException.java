package com.leoh.hhweek2.domain.exception;

public class EnrollmentLimitExceededException extends RuntimeException {
    public EnrollmentLimitExceededException(String message) {
        super(message);
    }
}
