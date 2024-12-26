package com.leoh.hhweek2.interfaces.api.lecture;

import com.leoh.hhweek2.domain.lecture.LectureServiceResponse;

public record AvailableLectureSearchResponse(
        Long lectureId,
        String lectureName,
        String speaker,
        int capacity,
        int currentEnrollmentCount
) {

    public static AvailableLectureSearchResponse fromServiceResponse(LectureServiceResponse serviceResponse) {
        return new AvailableLectureSearchResponse(
                serviceResponse.id(),
                serviceResponse.name(),
                serviceResponse.speaker(),
                serviceResponse.capacity(),
                serviceResponse.currentEnrollmentCount()
        );
    }
}
