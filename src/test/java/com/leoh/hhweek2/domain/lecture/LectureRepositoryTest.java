package com.leoh.hhweek2.domain.lecture;

import com.leoh.hhweek2.infrastructures.core.lecture.LectureRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(LectureRepositoryImpl.class)
class LectureRepositoryTest {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("특강 ID로 특강 조회 시 해당 특강이 없으면 null을 반환한다")
    void findById_whenLectureDoesNotExist_thenThrowsLectureNotFoundException() {
        // given
        Long lectureId = 1L;

        // when
        Lecture lecture = lectureRepository.findById(lectureId);

        // then
        assertThat(lecture).isNull();
    }

    @Test
    @DisplayName("특강 ID로 특강을 조회한다.")
    void findById_success() {
        // given
        String lectureName = "특강명";
        String speaker = "강연자";
        String description = "이런 저런 특강";
        Lecture lecture = Lecture.builder()
                .name(lectureName)
                .speaker(speaker)
                .description(description)
                .lectureDateTime(LocalDateTime.now())
                .build();

        Lecture savedLecture = lectureRepository.save(lecture);
        em.flush();

        // when
        Lecture findLecture = lectureRepository.findById(savedLecture.getId());

        // then
        assertThat(findLecture)
                .extracting("name", "speaker", "description")
                .containsExactly(lectureName, speaker, description);
    }

    @Test
    @DisplayName("전체 특강 조회 성공")
    void findByAll_success() {
        // given
        int lectureCount = 4;

        for (int i = 0; i < lectureCount; i++) {
            Lecture lecture = Lecture.builder().build();
            lectureRepository.save(lecture);
        }

        em.flush();

        // when
        List<Lecture> findLectures = lectureRepository.findAll();

        // then
        assertThat(findLectures).hasSize(lectureCount);
    }

}
