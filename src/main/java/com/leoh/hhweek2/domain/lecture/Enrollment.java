package com.leoh.hhweek2.domain.lecture;

import com.leoh.hhweek2.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="enrollment", uniqueConstraints = {
        @UniqueConstraint(
                name="lecture_user_uk",
                columnNames={"lecture_id","user_id"}
        )}, indexes = @Index(name = "idx_user_id_enrollment", columnList = "user_id"))
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "user_id")
    private Long userId;

}
