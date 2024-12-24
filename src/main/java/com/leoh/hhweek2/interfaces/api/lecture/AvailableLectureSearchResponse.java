package com.leoh.hhweek2.interfaces.api.lecture;

public record AvailableLectureSearchResponse(
        Long lectureId,
        String lectureName,
        String speaker,
        boolean isEnrolled,
        int currentEnrollmentCount,
        int capacity
) {
}
