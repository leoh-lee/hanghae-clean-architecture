package com.leoh.hhweek2.domain.exception;

public class DuplicatedEnrollmentException extends RuntimeException {

    public DuplicatedEnrollmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
