package com.goormthon.demo.service;

import com.goormthon.demo.domain.Coast;
import com.goormthon.demo.domain.CoastType;
import com.goormthon.demo.domain.Place;
import com.goormthon.demo.domain.Record;
import com.goormthon.demo.dto.SurveyRequest;
import com.goormthon.demo.repository.CoastRepository;
import com.goormthon.demo.repository.HoldingCreatureRepository;
import com.goormthon.demo.repository.PlaceRepository;
import com.goormthon.demo.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoastService {
    private final CoastRepository coastRepository;
    private final HoldingCreatureRepository holdingCreatureRepository;
    private final PlaceRepository placeRepository;
    private final RecordRepository recordRepository;

    @Transactional
    public String save(SurveyRequest req) {

        CoastType resultType = getCoastResult(req);
        Record record = Record.builder()
                .coastType(resultType)
                .build();
        recordRepository.save(record);

        return resultType.toString().toLowerCase();
    }

    public CoastType getCoastResult(SurveyRequest req) {
        // TODO 로직 작성

        // TODO 하나라도 답변이 넘어오지 않는다면 예외 처리
        return CoastType.CHAGWIDO;
    }


}
