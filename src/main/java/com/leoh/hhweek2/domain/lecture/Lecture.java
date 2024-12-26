package com.leoh.hhweek2.domain.lecture;

import com.leoh.hhweek2.domain.common.BaseEntity;
import com.leoh.hhweek2.domain.lecture.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime lectureDateTime;

    private String speaker;

    private int capacity;

    private String description;

    @OneToMany(mappedBy = "lecture")
    private List<Enrollment> enrollments = new ArrayList<>();

    public boolean isScheduledWithin(LocalDate startDate, LocalDate endDate) {
        boolean isAfterStart = (startDate == null) ||
                !lectureDateTime.isBefore(startDate.atStartOfDay());

        boolean isBeforeEnd = (endDate == null) ||
                !lectureDateTime.isAfter(endDate.plusDays(1).atStartOfDay());

        return isAfterStart && isBeforeEnd;
    }

    public boolean canEnroll() {
        return capacity > this.enrollments.size();
    }

    public int getCurrentEnrollmentCount() {
        return this.enrollments.size();
    }

}
