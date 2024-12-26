package com.leoh.hhweek2.domain.lecture.dto;

import com.leoh.hhweek2.domain.lecture.Lecture;

import java.time.LocalDateTime;

public record LectureServiceResponse(
        Long id,
        String name,
        LocalDateTime lectureDateTime,
        String speaker,
        String description,
        int capacity,
        int currentEnrollmentCount
) {
    public static LectureServiceResponse fromEntity(Lecture lecture) {
        return new LectureServiceResponse(
                lecture.getId(),
                lecture.getName(),
                lecture.getLectureDateTime(),
                lecture.getSpeaker(),
                lecture.getDescription(),
                lecture.getCapacity(),
                lecture.getCurrentEnrollmentCount()
        );
    }
}
