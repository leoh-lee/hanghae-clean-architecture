package com.leoh.hhweek2.infrastructures.core.enrollment;

import com.leoh.hhweek2.domain.lecture.Enrollment;
import com.leoh.hhweek2.domain.lecture.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    @Override
    public List<Enrollment> findEnrollmentsByUserId(Long userId) {
        return enrollmentJpaRepository.findAllByUserId(userId);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentJpaRepository.save(enrollment);
    }

    @Override
    public void deleteAllInBatch() {
        enrollmentJpaRepository.deleteAllInBatch();
    }
}
