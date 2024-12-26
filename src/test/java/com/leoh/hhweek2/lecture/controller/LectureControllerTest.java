package com.leoh.hhweek2.lecture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leoh.hhweek2.application.lecture.LectureFacade;
import com.leoh.hhweek2.interfaces.api.lecture.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class LectureControllerTest {

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LectureFacade lectureFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("특강 수강신청 API")
    void enroll() throws Exception {
        // given
        long lectureId = 1L;
        long userId = 1L;

        EnrollRequest enrollRequest = new EnrollRequest(userId);

        when(lectureFacade.enroll(lectureId, enrollRequest)).thenReturn(new EnrollResponse(lectureId, "특강1", userId, "강연자"));

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/lectures/"+ lectureId +"/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enrollRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("특강 신청 가능 목록 API")
    void getAvailableLectures() throws Exception {
        // given
        long lectureId = 1L;
        String lectureName = "강의명";
        String speaker = "강연자";

        LocalDate startTime = LocalDate.now().minusDays(7L);
        LocalDate endTime = LocalDate.now();

        AvailableLectureSearchRequest searchRequest = new AvailableLectureSearchRequest(1L, startTime, endTime);
        AvailableLectureSearchResponse response = new AvailableLectureSearchResponse(lectureId, lectureName, speaker, 18, 30);

        when(lectureFacade.getAvailableLectures(searchRequest)).thenReturn(List.of(response));

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lectures/available")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("startTime", startTime.format(DATE_PATTERN))
                        .queryParam("endTime", endTime.format(DATE_PATTERN))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("특강 신청 완료 목록 조회 API")
    void getUserEnrollment() throws Exception {
        // given
        long userId = 1L;

        UserEnrollmentSearchResponse response = new UserEnrollmentSearchResponse(1L, "강의명", "강연자", 13, 30);

        when(lectureFacade.getUserEnrollment(userId)).thenReturn(List.of(response));

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + userId + "/enrollment")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}