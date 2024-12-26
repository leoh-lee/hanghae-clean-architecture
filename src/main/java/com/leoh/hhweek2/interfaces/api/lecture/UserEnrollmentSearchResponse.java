package com.leoh.hhweek2.interfaces.api.lecture;

import com.leoh.hhweek2.domain.lecture.UserEnrollmentSearchServiceResponse;

public record UserEnrollmentSearchResponse(
        Long lectureId,
        String lectureName,
        String speaker,
        int currentEnrollmentCount,
        int capacity
) {
    public static UserEnrollmentSearchResponse fromServiceResponse(UserEnrollmentSearchServiceResponse serviceResponse) {
        return new UserEnrollmentSearchResponse(
                serviceResponse.lectureId(),
                serviceResponse.lectureName(),
                serviceResponse.speaker(),
                serviceResponse.currentEnrollmentCount(),
                serviceResponse.capacity()
        );
    }
}
