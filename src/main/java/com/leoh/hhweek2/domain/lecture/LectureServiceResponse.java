package com.leoh.hhweek2.domain.lecture;

import java.time.LocalDateTime;

public record LectureServiceResponse(
        Long id,
        String name,
        LocalDateTime lectureDateTime,
        String speaker,
        String description
) {
    public static LectureServiceResponse fromEntity(Lecture lecture) {
        return new LectureServiceResponse(lecture.getId(), lecture.getName(), lecture.getLectureDateTime(), lecture.getSpeaker(), lecture.getDescription());
    }
}
