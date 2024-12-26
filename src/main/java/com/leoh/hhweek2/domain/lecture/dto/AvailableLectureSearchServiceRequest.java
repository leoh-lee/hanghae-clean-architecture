package com.leoh.hhweek2.domain.lecture.dto;

import java.time.LocalDate;

public record AvailableLectureSearchServiceRequest(
        Long userId,
        LocalDate startDate,
        LocalDate endDate
) {
}
