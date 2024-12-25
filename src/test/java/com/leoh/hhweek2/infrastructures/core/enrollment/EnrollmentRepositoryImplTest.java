package com.leoh.hhweek2.infrastructures.core.enrollment;

import com.leoh.hhweek2.domain.lecture.Lecture;
import com.leoh.hhweek2.domain.lecture.LectureRepository;
import com.leoh.hhweek2.domain.lecture.enrollment.Enrollment;
import com.leoh.hhweek2.domain.lecture.enrollment.EnrollmentRepository;
import com.leoh.hhweek2.infrastructures.core.lecture.LectureRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import({EnrollmentRepositoryImpl.class, LectureRepositoryImpl.class})
class EnrollmentRepositoryImplTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("특정 사용자가 신청한 수강 신청을 조회한다.")
    void findEnrollmentsByUserId_success() {
        // given
        Long targetUserId = 1L;
        Long nonTargetUserId = 2L;

        Lecture lecture1 = Lecture.builder().name("특강1").build();
        Lecture lecture2 = Lecture.builder().name("특강2").build();

        lectureRepository.save(lecture1);
        lectureRepository.save(lecture2);

        Enrollment enrollment1 = Enrollment
                .builder()
                .userId(targetUserId)
                .lecture(lecture1)
                .build();

        Enrollment enrollment2 = Enrollment
                .builder()
                .userId(targetUserId)
                .lecture(lecture2)
                .build();

        Enrollment enrollment3 = Enrollment
                .builder()
                .userId(nonTargetUserId)
                .lecture(lecture2)
                .build();

        enrollmentRepository.save(enrollment1);
        enrollmentRepository.save(enrollment2);
        enrollmentRepository.save(enrollment3);

        em.flush();

        // when
        List<Enrollment> findEnrollments = enrollmentRepository.findEnrollmentsByUserId(targetUserId);

        // then
        assertThat(findEnrollments).hasSize(2);
    }
}