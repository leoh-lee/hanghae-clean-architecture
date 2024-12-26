package com.leoh.hhweek2.domain.lecture;

import java.util.List;

public interface EnrollmentRepository {

    List<Enrollment> findEnrollmentsByUserId(Long userId);

    Enrollment save(Enrollment enrollment);

    void deleteAllInBatch();

}
