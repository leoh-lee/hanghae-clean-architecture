package com.leoh.hhweek2.domain.lecture;

import com.leoh.hhweek2.domain.lecture.enrollment.Enrollment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    @DisplayName("정원보다 신청인원이 적은 지 확인하여 신청 가능 여부를 조회한다.")
    void canEnroll_returnsTrue_whenCapcityGreaterThanEnrollmentsSize() {
        // given
        int capacity = 30;
        int availableEnrollmentCount = 29;
        int unavailableEnrollmentCount = capacity;

        List<Enrollment> availableEnrollments = new ArrayList<>();
        for (int i = 0; i < availableEnrollmentCount; i++) {
            availableEnrollments.add(Enrollment.builder().build());
        }

        List<Enrollment> unavailableEnrollments = new ArrayList<>();
        for (int i = 0; i < unavailableEnrollmentCount; i++) {
            unavailableEnrollments.add(Enrollment.builder().build());
        }

        Lecture availableLecture = Lecture.builder().capacity(capacity).enrollments(availableEnrollments).build();
        Lecture unavailableLecture = Lecture.builder().capacity(capacity).enrollments(unavailableEnrollments).build();

        // when
        boolean availableResult = availableLecture.canEnroll();
        boolean unavailableResult = unavailableLecture.canEnroll();

        // then
        assertThat(availableResult).isTrue();
        assertThat(unavailableResult).isFalse();
    }

    @Test
    @DisplayName("특강 신청 개수를 조회한다.")
    void getCurrentEnrollmentCount_returnsEnrollmentsSize() {
        // given
        List<Enrollment> enrollments = List.of(
                Enrollment.builder().build(),
                Enrollment.builder().build()
        );

        Lecture lecture = Lecture.builder().enrollments(enrollments).build();

        // when
        int enrollmentCount = lecture.getCurrentEnrollmentCount();

        // then
        assertThat(enrollmentCount).isEqualTo(enrollments.size());
    }
}