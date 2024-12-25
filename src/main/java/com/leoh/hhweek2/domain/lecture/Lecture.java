package com.leoh.hhweek2.domain.lecture;

import com.leoh.hhweek2.domain.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public boolean isScheduledWithin(LocalDate startDate, LocalDate endDate) {
        boolean isAfterStart = (startDate == null) ||
                !lectureDateTime.isBefore(startDate.atStartOfDay());

        boolean isBeforeEnd = (endDate == null) ||
                !lectureDateTime.isAfter(endDate.plusDays(1).atStartOfDay());

        return isAfterStart && isBeforeEnd;
    }
}
