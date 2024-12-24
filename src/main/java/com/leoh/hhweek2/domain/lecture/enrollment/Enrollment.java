package com.leoh.hhweek2.domain.lecture.enrollment;

import com.leoh.hhweek2.domain.common.BaseEntity;
import com.leoh.hhweek2.domain.lecture.Lecture;
import jakarta.persistence.*;

@Entity
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;

    private Long userId;

}
