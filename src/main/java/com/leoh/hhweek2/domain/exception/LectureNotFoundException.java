package com.leoh.hhweek2.domain.exception;

public class LectureNotFoundException extends RuntimeException {

    public LectureNotFoundException() {
    }

    public LectureNotFoundException(String message) {
        super(message);
    }
}
