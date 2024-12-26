package com.leoh.hhweek2.interfaces.api.lecture;

import com.leoh.hhweek2.domain.lecture.LectureServiceResponse;
import com.leoh.hhweek2.domain.lecture.enrollment.EnrollmentServiceResponse;

public record EnrollResponse(
        long lectureId,
        String lectureName,
        long userId,
        String speaker
) {
    public static EnrollResponse fromServiceResponse(EnrollmentServiceResponse serviceResponse) {
        LectureServiceResponse lecture = serviceResponse.lecture();
        return new EnrollResponse(lecture.id(), lecture.name(), serviceResponse.userId(), lecture.speaker());
    }
}
