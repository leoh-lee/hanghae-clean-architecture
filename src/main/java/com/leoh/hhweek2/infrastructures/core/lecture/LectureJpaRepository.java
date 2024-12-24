package com.leoh.hhweek2.infrastructures.core.lecture;

import com.leoh.hhweek2.domain.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<Long, Lecture> {
}
