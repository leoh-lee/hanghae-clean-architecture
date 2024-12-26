package com.leoh.hhweek2.application.lecture;

import com.leoh.hhweek2.domain.lecture.LectureService;
import com.leoh.hhweek2.domain.lecture.enrollment.EnrollmentService;
import com.leoh.hhweek2.domain.lecture.enrollment.EnrollmentServiceResponse;
import com.leoh.hhweek2.interfaces.api.lecture.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureFacade {

    private final LectureService lectureService;
    private final EnrollmentService enrollmentService;

    public EnrollResponse enroll(long lectureId, EnrollRequest enrollRequest) {
        EnrollmentServiceResponse enrollmentServiceResponse = enrollmentService.enroll(lectureId, enrollRequest.userId());
        return EnrollResponse.fromServiceResponse(enrollmentServiceResponse);
    }

    public List<AvailableLectureSearchResponse> getAvailableLectures(final AvailableLectureSearchRequest searchRequest) {
        return lectureService.getAvailableLectures(searchRequest)
                .stream()
                .map(AvailableLectureSearchResponse::fromServiceResponse)
                .toList();
    }

    public List<UserEnrollmentSearchResponse> getUserEnrollment(long userId) {
        return null;
    }
}
