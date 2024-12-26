package com.leoh.hhweek2.domain.lecture.dto;

import com.leoh.hhweek2.domain.lecture.Lecture;

public record UserEnrollmentSearchServiceResponse(
        Long lectureId,
        String lectureName,
        String speaker,
        int currentEnrollmentCount,
        int capacity
) {
    public static UserEnrollmentSearchServiceResponse fromEntity(Lecture lecture) {
        return new UserEnrollmentSearchServiceResponse(
                lecture.getId(),
                lecture.getName(),
                lecture.getSpeaker(),
                lecture.getCurrentEnrollmentCount(),
                lecture.getCapacity()
        );
    }
}
