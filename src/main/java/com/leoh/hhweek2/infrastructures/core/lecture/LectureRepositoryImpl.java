package com.leoh.hhweek2.infrastructures.core.lecture;

import com.leoh.hhweek2.domain.lecture.Lecture;
import com.leoh.hhweek2.domain.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public Lecture findById(Long lectureId) {
        return lectureJpaRepository.findById(lectureId).orElse(null);
    }

    @Override
    public Lecture findByIdWithLock(Long lectureId) {
        return lectureJpaRepository.findByIdWithLock(lectureId).orElse(null);
    }

    @Override
    public List<Lecture> findAll() {
        return lectureJpaRepository.findAll();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureJpaRepository.save(lecture);
    }

    @Override
    public void deleteAllInBatch() {
        lectureJpaRepository.deleteAllInBatch();
    }
}
