package com.leoh.hhweek2.domain.lecture;

import com.leoh.hhweek2.domain.lecture.enrollment.EnrollmentRepository;
import com.leoh.hhweek2.interfaces.api.lecture.AvailableLectureSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final EnrollmentRepository enrollmentRepository;

    public List<LectureServiceResponse> getAvailableLectures(AvailableLectureSearchRequest searchRequest) {
        List<Lecture> lecturesAll = getLecturesAll();

        // 사용자가 신청한 특강 IDs
        List<Long> userEnrollmentLectureIds = getUserEnrollmentLectureIds(searchRequest);

        // 검색 기간 내에 신청가능한 강의 목록
        List<Lecture> availableLectures = getAvailableLectureInSearchDate(searchRequest, lecturesAll);

        // 사용자가 신청하지 않은 특강 목록 반환
        return availableLectures.stream()
                .filter(lecture -> !userEnrollmentLectureIds.contains(lecture.getId()))
                .map(LectureServiceResponse::fromEntity)
                .toList();
    }

    public Lecture getLectureById(long id) {
        return null;
    }

    private List<Lecture> getAvailableLectureInSearchDate(AvailableLectureSearchRequest searchRequest, List<Lecture> lecturesAll) {
        return lecturesAll.stream()
                .filter(lecture -> lecture.isScheduledWithin(searchRequest.startDate(), searchRequest.endDate()))
                .filter(Lecture::canEnroll)
                .toList();
    }

    private List<Lecture> getLecturesAll() {
        return lectureRepository.findAll();
    }

    private List<Long> getUserEnrollmentLectureIds(AvailableLectureSearchRequest searchRequest) {
        return enrollmentRepository.findEnrollmentsByUserId(searchRequest.userId())
                .stream()
                .map(enrollment -> enrollment.getLecture().getId())
                .toList();
    }
}
