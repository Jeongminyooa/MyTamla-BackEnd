package com.goormthon.demo.service;

import com.goormthon.demo.domain.*;
import com.goormthon.demo.dto.CoastResponse;
import com.goormthon.demo.dto.CreatureResponse;
import com.goormthon.demo.dto.PlaceResponse;
import com.goormthon.demo.dto.SurveyRequest;
import com.goormthon.demo.repository.CoastRepository;
import com.goormthon.demo.repository.HoldingCreatureRepository;
import com.goormthon.demo.repository.PlaceRepository;
import com.goormthon.demo.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CoastService {
    private final CoastRepository coastRepository;
    private final HoldingCreatureRepository holdingCreatureRepository;
    private final PlaceRepository placeRepository;
    private final RecordRepository recordRepository;

    @Transactional
    public String save(SurveyRequest req) {

        CoastType resultType = calculateCoastResult(req);
        Record record = Record.builder()
                .coastType(resultType)
                .build();
        recordRepository.save(record);

        return resultType.toString().toLowerCase();
    }

    public CoastType calculateCoastResult(SurveyRequest req) {
        // TODO 로직 작성

        // TODO 하나라도 답변이 넘어오지 않는다면 예외 처리
        return CoastType.CHAGWIDO;
    }


    @Transactional(readOnly = true)
    public List<CoastResponse> getCoastResult(String coastType) {
        List<Coast> coast = coastRepository.findByCoastType(CoastType.valueOf(coastType.toUpperCase()));

        List<CoastResponse> responses = new ArrayList<>();
        for(Coast c : coast) {
            // 생물체 추가
            List<CreatureResponse> holdingCreatures = new ArrayList<>();
            for(HoldingCreature creature : c.getCreatures()) {
                CreatureResponse item = CreatureResponse.builder()
                        .image(creature.getImage())
                        .build();
                holdingCreatures.add(item);
            }

            // 장소 추가
            List<PlaceResponse> places = new ArrayList<>();
            for(Place place : c.getPlaces()) {
                PlaceResponse item = PlaceResponse.builder()
                        .image(place.getImage())
                        .url(place.getUrl())
                        .build();

                places.add(item);
            }

            // 해안 결과
            CoastResponse response = CoastResponse.builder()
                    .dateType(c.getDateType().toString().toLowerCase())
                    .coastalName(c.getCoastType().getName())
                    .coastalType(c.getCoastType().toString())
                    .coastalImage(c.getCoastalImage())
                    .coastalContent(c.getCoastalContent())
                    .holdingCreature(holdingCreatures)
                    .creatureContent(c.getCreatureContent())
                    .location(c.getLocation())
                    .locationImage(c.getLocationImage())
                    .place(places)
                    .friend(c.getFriend())
                    .enemy(c.getEnemy())
                    .build();

            responses.add(response);
        }
        return responses;
    }
}
