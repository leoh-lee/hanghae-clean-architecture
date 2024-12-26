package com.leoh.hhweek2.infrastructures.core.lecture;

import com.leoh.hhweek2.domain.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

    @Query("select l from Lecture l left join fetch l.enrollments")
    List<Lecture> findAll();

}
