package com.leoh.hhweek2.domain.lecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class LectureTest {

    @Test
    @DisplayName("조회할 날짜 범위가 없는 경우 true를 반환한다.")
    void isScheduledWithin_whenIsRangeNull_returnsTrue() {
        // given
        Lecture lecture = Lecture.builder().build();

        // when
        boolean result = lecture.isScheduledWithin(null, null);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("특강이 날짜 범위 내에 있을 경우 true를 반환한다.")
    void isScheduledWithin_returnsTrueWhenWithinRange() {
        // given
        LocalDateTime lectureDateTime = LocalDateTime.now();
        Lecture lecture = Lecture.builder().lectureDateTime(lectureDateTime).build();

        LocalDate startDate = LocalDate.now().minusDays(1L);
        LocalDate endDate = LocalDate.now().plusDays(1L);

        // when
        boolean result = lecture.isScheduledWithin(startDate, endDate);
        boolean result2 = lecture.isScheduledWithin(null, endDate);
        boolean result3 = lecture.isScheduledWithin(startDate, null);

        // then
        assertThat(result).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isTrue();
    }
}