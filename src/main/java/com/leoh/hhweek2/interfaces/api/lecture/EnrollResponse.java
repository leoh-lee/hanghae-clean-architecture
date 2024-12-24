package com.leoh.hhweek2.interfaces.api.lecture;

public record EnrollResponse(
        long lectureId,
        long lectureName,
        String speaker
) {
}
