package com.leoh.hhweek2.interfaces.api.lecture.dto;

import com.leoh.hhweek2.domain.lecture.dto.AvailableLectureSearchServiceRequest;

import java.time.LocalDate;

public record AvailableLectureSearchRequest(
        Long userId,
        LocalDate startDate,
        LocalDate endDate
) {
    public AvailableLectureSearchServiceRequest toServiceRequest() {
        return new AvailableLectureSearchServiceRequest(userId, startDate, endDate);
    }
}
