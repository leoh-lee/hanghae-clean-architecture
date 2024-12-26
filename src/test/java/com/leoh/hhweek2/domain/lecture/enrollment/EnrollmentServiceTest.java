package com.leoh.hhweek2.domain.lecture.enrollment;

import com.leoh.hhweek2.domain.exception.LectureNotFoundException;
import com.leoh.hhweek2.domain.lecture.Lecture;
import com.leoh.hhweek2.domain.lecture.LectureRepository;
import com.leoh.hhweek2.domain.lecture.LectureServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private LectureRepository lectureRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    @DisplayName("사용자 ID로 사용자가의 특강 신청 목록을 조회한다.")
    void getEnrollmentByUserId_success() {
        // given
        long userId = 1L;

        List<Enrollment> enrollments = List.of(
                Enrollment.builder().id(1L).userId(userId).lecture(Lecture.builder().capacity(30).enrollments(List.of()).build()).build(),
                Enrollment.builder().id(2L).userId(userId).lecture(Lecture.builder().capacity(30).enrollments(List.of()).build()).build(),
                Enrollment.builder().id(3L).userId(userId).lecture(Lecture.builder().capacity(30).enrollments(List.of()).build()).build()
        );

        when(enrollmentRepository.findEnrollmentsByUserId(userId)).thenReturn(enrollments);

        // when
        List<EnrollmentServiceResponse> serviceResponses = enrollmentService.getEnrollmentByUserId(userId);

        // then
        assertThat(serviceResponses).hasSize(enrollments.size());
    }

    @Test
    @DisplayName("특강 신청 시 해당하는 특강이 없으면 예외 발생")
    void enroll_whenIsLectureNull_throwsLectureNotFoundException() {
        // given
        long lectureId = 1L;
        when(lectureRepository.findById(lectureId)).thenReturn(null);

        // when // then
        assertThatThrownBy(() -> enrollmentService.enroll(lectureId, 1L))
                .isInstanceOf(LectureNotFoundException.class);
    }

    @Test
    @DisplayName("특강 신청 성공")
    void enroll_success() {
        // given
        long lectureId = 1L;
        long userId = 1L;
        Lecture lecture = Lecture.builder().id(lectureId).enrollments(List.of()).build();
        Enrollment enrollment = Enrollment.builder().userId(userId).lecture(lecture).build();

        when(lectureRepository.findById(lectureId)).thenReturn(lecture);
        when(enrollmentRepository.save(any())).thenReturn(enrollment);

        // when
        EnrollmentServiceResponse result = enrollmentService.enroll(lectureId, userId);

        // then
        assertThat(result).extracting("userId", "lecture")
                .containsExactly(userId, LectureServiceResponse.fromEntity(lecture));
    }

}
