package com.leoh.hhweek2.application.lecture;

import com.leoh.hhweek2.interfaces.api.lecture.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnrollFacade {

    public EnrollResponse enroll(long lectureId, EnrollRequest enrollRequest) {
        return null;
    }

    public List<AvailableLectureSearchResponse> getAvailableLectures(AvailableLectureSearchRequest searchRequest) {
        return null;
    }

    public List<UserEnrollmentSearchResponse> getUserEnrollment(long userId) {
        return null;
    }
}
