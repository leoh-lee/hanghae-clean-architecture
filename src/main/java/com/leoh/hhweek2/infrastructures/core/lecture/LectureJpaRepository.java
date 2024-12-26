package com.leoh.hhweek2.infrastructures.core.lecture;

import com.leoh.hhweek2.domain.lecture.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

    @Query("select l from Lecture l left join fetch l.enrollments")
    List<Lecture> findAll();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from Lecture l where l.id = :id")
    Optional<Lecture> findByIdWithLock(long id);

}
