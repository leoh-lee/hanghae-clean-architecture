package com.leoh.hhweek2.domain.lecture;

import com.leoh.hhweek2.interfaces.api.lecture.AvailableLectureSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<LectureServiceResponse> getAvailableLectures(AvailableLectureSearchRequest searchRequest) {
        return lectureRepository.findAll().stream()
                .filter(lecture -> lecture.isScheduledWithin(searchRequest.startDate(), searchRequest.endDate()))
                .map(LectureServiceResponse::fromEntity)
                .toList();
    }
}

