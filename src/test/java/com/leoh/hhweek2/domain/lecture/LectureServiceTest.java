package com.leoh.hhweek2.domain.lecture;

import com.leoh.hhweek2.domain.lecture.enrollment.Enrollment;
import com.leoh.hhweek2.domain.lecture.enrollment.EnrollmentRepository;
import com.leoh.hhweek2.interfaces.api.lecture.AvailableLectureSearchRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private LectureService lectureService;

    @Test
    @DisplayName("조회 날짜 범위 내의 특강을 조회한다.")
    void getAvailableLecturesWithinDateRange() {
        // given
        long userId = 1L;
        List<Lecture> lectures = createMockLectures();

        LocalDate startDate = LocalDate.now().minusDays(7L);
        LocalDate endDate = LocalDate.now();

        AvailableLectureSearchRequest searchRequest = new AvailableLectureSearchRequest(1L, startDate, endDate);

        when(lectureRepository.findAll()).thenReturn(lectures);
        when(enrollmentRepository.findEnrollmentsByUserId(userId)).thenReturn(List.of());
        // when
        List<LectureServiceResponse> serviceResponse = lectureService.getAvailableLectures(searchRequest);

        // then
        assertThat(serviceResponse).hasSize(3);
        assertThat(serviceResponse).extracting("name", "speaker", "description")
                .containsExactlyInAnyOrder(
                        tuple("특강2", "강연자2", "설명2"),
                        tuple("특강3", "강연자3", "설명3"),
                        tuple("특강4", "강연자4", "설명4")
                );
    }

    @Test
    @DisplayName("조회 날짜가 없는 경우 신청가능한 모든 특강을 조회한다.")
    void getAvailableLecturesWithoutDateRange() {
        // given
        List<Lecture> lectures = createMockLectures();

        AvailableLectureSearchRequest searchRequest = new AvailableLectureSearchRequest(1L, null, null);

        when(lectureRepository.findAll()).thenReturn(lectures);

        // when
        List<LectureServiceResponse> serviceResponse = lectureService.getAvailableLectures(searchRequest);

        // then
        assertThat(serviceResponse).hasSize(4);
        assertThat(serviceResponse).extracting("name", "speaker", "description")
                .containsExactlyInAnyOrder(
                        tuple("특강1", "강연자1", "설명1"),
                        tuple("특강2", "강연자2", "설명2"),
                        tuple("특강3", "강연자3", "설명3"),
                        tuple("특강4", "강연자4", "설명4")
                );
    }

    @Test
    @DisplayName("사용자가 이미 신청한 특강은 조회대상에서 제외한다.")
    void getAvailableLecturesWithoutAlreadyEnrolled() {
        // given
        long userId = 1L;
        List<Lecture> lectures = createMockLectures();

        List<Enrollment> enrollments = List.of(
                Enrollment.builder().userId(userId).lecture(lectures.get(0)).build(),
                Enrollment.builder().userId(userId).lecture(lectures.get(1)).build(),
                Enrollment.builder().userId(userId).lecture(lectures.get(2)).build()
        );

        AvailableLectureSearchRequest searchRequest = new AvailableLectureSearchRequest(1L, null, null);

        when(lectureRepository.findAll()).thenReturn(lectures);
        when(enrollmentRepository.findEnrollmentsByUserId(userId)).thenReturn(enrollments);

        // when
        List<LectureServiceResponse> serviceResponse = lectureService.getAvailableLectures(searchRequest);

        // then
        Lecture lecture = lectures.get(3); // userId가 신청하지 않는 특강

        assertThat(serviceResponse).hasSize(1);
        assertThat(serviceResponse).extracting("id", "name", "speaker", "description")
                .containsExactly(
                        tuple(lecture.getId(), lecture.getName(), lecture.getSpeaker(), lecture.getDescription())
                );
    }


    private List<Lecture> createMockLectures() {
        return List.of(
                Lecture.builder().id(1L).name("특강1").speaker("강연자1").description("설명1").lectureDateTime(LocalDateTime.now().minusDays(8L)).capacity(30).enrollments(List.of()).build(),
                Lecture.builder().id(2L).name("특강2").speaker("강연자2").description("설명2").lectureDateTime(LocalDateTime.now().minusDays(7L)).capacity(30).enrollments(List.of()).build(),
                Lecture.builder().id(3L).name("특강3").speaker("강연자3").description("설명3").lectureDateTime(LocalDateTime.now().minusDays(1L)).capacity(30).enrollments(List.of()).build(),
                Lecture.builder().id(4L).name("특강4").speaker("강연자4").description("설명4").lectureDateTime(LocalDateTime.now()).capacity(30).enrollments(List.of()).build()
        );
    }

}