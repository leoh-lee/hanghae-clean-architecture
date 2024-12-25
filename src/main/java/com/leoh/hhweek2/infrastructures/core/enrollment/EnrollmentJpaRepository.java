package com.leoh.hhweek2.infrastructures.core.enrollment;

import com.leoh.hhweek2.domain.lecture.enrollment.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentJpaRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByUserId(Long userId);
}
