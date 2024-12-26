package com.leoh.hhweek2.domain.lecture.dto;

import com.leoh.hhweek2.domain.lecture.Enrollment;

public record EnrollmentServiceResponse(
        Long id,
        LectureServiceResponse lecture,
        Long userId
) {
    public static EnrollmentServiceResponse fromEntity(Enrollment enrollment) {
        return new EnrollmentServiceResponse(enrollment.getId(), LectureServiceResponse.fromEntity(enrollment.getLecture()), enrollment.getUserId());
    }

    public Long getLectureId() {
        return lecture.id();
    }
}
