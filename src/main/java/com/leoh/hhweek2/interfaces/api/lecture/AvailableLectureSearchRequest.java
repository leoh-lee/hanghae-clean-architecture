package com.leoh.hhweek2.interfaces.api.lecture;

import java.time.LocalDate;

public record AvailableLectureSearchRequest(
        LocalDate startDate,
        LocalDate endDate
) {
}
