package com.leoh.hhweek2.domain.lecture.enrollment;

import com.leoh.hhweek2.domain.exception.LectureNotFoundException;
import com.leoh.hhweek2.domain.lecture.Lecture;
import com.leoh.hhweek2.domain.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnrollmentService {

    private final LectureRepository lectureRepository;
    private final EnrollmentRepository enrollmentRepository;

    public List<EnrollmentServiceResponse> getEnrollmentByUserId(Long userId) {
        return enrollmentRepository.findEnrollmentsByUserId(userId).stream()
                .map(EnrollmentServiceResponse::fromEntity)
                .toList();
    }

    @Transactional
    public EnrollmentServiceResponse enroll(long lectureId, long userId) {
        Lecture lecture = lectureRepository.findById(lectureId);

        if (lecture == null) {
            throw new LectureNotFoundException("존재하지 않는 특강입니다.");
        }

        Enrollment enrollment = Enrollment.builder()
                .userId(userId)
                .lecture(lecture)
                .build();

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return EnrollmentServiceResponse.fromEntity(savedEnrollment);
    }
}
