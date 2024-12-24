package com.leoh.hhweek2.interfaces.api.lecture;

public record UserEnrollmentSearchResponse(
        Long lectureId,
        String lectureName,
        String speaker,
        int currentEnrollmentCount,
        int capacity
) {
}
