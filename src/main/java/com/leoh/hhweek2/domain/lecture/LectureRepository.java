package com.leoh.hhweek2.domain.lecture;

import java.util.List;

public interface LectureRepository {

    Lecture findById(Long lectureId);

    List<Lecture> findAll();

    Lecture save(Lecture lecture);
}
