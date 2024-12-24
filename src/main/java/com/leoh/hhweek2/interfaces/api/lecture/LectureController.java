package com.leoh.hhweek2.interfaces.api.lecture;

import com.leoh.hhweek2.application.lecture.EnrollFacade;
import com.leoh.hhweek2.support.http.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LectureController {

    private final EnrollFacade enrollFacade;

    @PostMapping("/lectures/{lectureId}/enroll")
    public ApiResponse<EnrollResponse> enroll(
            @PathVariable("lectureId") long lectureId,
            @RequestBody EnrollRequest enrollRequest
    ) {
        return ApiResponse.ok(enrollFacade.enroll(lectureId, enrollRequest));
    }

    @GetMapping("/lectures/available")
    public ApiResponse<List<AvailableLectureSearchResponse>> getAvailableLectures(AvailableLectureSearchRequest searchRequest) {
        return ApiResponse.ok(enrollFacade.getAvailableLectures(searchRequest));
    }

    @GetMapping("/users/{userId}/enrollment")
    public ApiResponse<List<UserEnrollmentSearchResponse>> getUserEnrollment(@PathVariable("userId") long userId) {
        return ApiResponse.ok(enrollFacade.getUserEnrollment(userId));
    }

}
