package com.leoh.hhweek2.application.lecture;

import com.leoh.hhweek2.domain.exception.EnrollmentLimitExceededException;
import com.leoh.hhweek2.domain.lecture.LectureService;
import com.leoh.hhweek2.domain.lecture.dto.EnrollmentServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class LectureFacadeTest {

    public static final int ENROLLMENT_CAPACITY = 30;

    Logger logger = LoggerFactory.getLogger(LectureFacadeTest.class);

    @Autowired
    private LectureService lectureService;

    @Test
    @DisplayName("동시에 동일한 특강에 대해 40명이 신청했을 때, 정원만큼만 성공한다.")
    void enroll_whenOverCapacity_throwsEnrollmentLimitExceededException() throws InterruptedException {
        // given
        int userCount = 40;
        long lectureId = 1L;

        List<EnrollmentServiceResponse> successEnrollments = new ArrayList<>();

        // when
        CountDownLatch latch = new CountDownLatch(userCount);
        try (ExecutorService es = Executors.newFixedThreadPool(userCount)) {
            for (int i = 0; i < userCount; i++) {
                long userId = (long) i + 1;
                es.execute(() -> {
                    try {
                        successEnrollments.add(lectureService.enroll(lectureId, userId));
                    } catch (EnrollmentLimitExceededException e) {
                        logger.info("사용자 {}의 신청이 정원 초과로 인해 실패하였습니다.", userId);
                    }
                    latch.countDown();
                });
            }
            latch.await();
            es.shutdown();
        }

        // then
        assertThat(successEnrollments).hasSize(ENROLLMENT_CAPACITY);
    }

}
