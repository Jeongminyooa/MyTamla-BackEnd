package com.goormthon.demo.service;

import com.goormthon.demo.domain.*;
import com.goormthon.demo.dto.CoastResponse;
import com.goormthon.demo.dto.CreatureResponse;
import com.goormthon.demo.dto.PlaceResponse;
import com.goormthon.demo.dto.SurveyRequest;
import com.goormthon.demo.repository.CoastRepository;
import com.goormthon.demo.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoastService {
    private final CoastRepository coastRepository;
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

    @Transactional(readOnly = true)
    public List<CoastResponse> getCoastResult(String coastType) {
        List<Coast> coast = coastRepository.findByCoastType(CoastType.valueOf(coastType.toUpperCase()));

        List<CoastResponse> responses = new ArrayList<>();
        for(Coast c : coast) {
            // 생물체 추가
            List<CreatureResponse> holdingCreatures = new ArrayList<>();
            for(HoldingCreature creature : c.getCreatures()) {
                CreatureResponse item = CreatureResponse.builder()
                        .name(creature.getName())
                        .image(creature.getImage())
                        .kind(creature.getCreatureType().getName())
                        .build();
                holdingCreatures.add(item);
            }

            // 장소 추가
            List<PlaceResponse> places = new ArrayList<>();
            for(Place place : c.getPlaces()) {
                PlaceResponse item = PlaceResponse.builder()
                        .image(place.getImage())
                        .url(place.getUrl())
                        .name(place.getName())
                        .time(place.getTime())
                        .location(place.getLocation())
                        .build();

                places.add(item);
            }

            // 해안 결과
            CoastResponse response = CoastResponse.builder()
                    .dateType(c.getDateType().toString().toLowerCase())
                    .coastalName(c.getCoastType().getName())
                    .coastalImage(c.getCoastalImage())
                    .coastalContent(c.getCoastalContent())
                    .holdingCreature(holdingCreatures)
                    .location(c.getLocation())
                    .locationImage(c.getLocationImage())
                    .place(places)
                    .friend(c.getFriend())
                    .friendName(c.getFriendName())
                    .enemy(c.getEnemy())
                    .enemyName(c.getEnemyName())
                    .build();

            responses.add(response);
        }
        return responses;
    }

    public CoastType calculateCoastResult(SurveyRequest req) {
        // b->c->i->s
        final int COAST_COUNT = 4;
        int[] totalScore = { 0, 0, 0, 0};

        int[][] scoreOfFirst = { {3, 3, 2, 1}, {2, 3, 2, 1}, {3, 1, 2, 3},
                                {1, 1, 3, 2}, {3, 3, 1, 3}, {2, 2, 1, 1} };
        int[][] scoreOfSecond = { {2, 3, 2, 1}, {1, 1, 1, 1}, {3, 2, 1, 1},
                                {1, 2, 3, 1}, {3, 2, 1, 1}, {2, 2, 1, 1} };
        int[][] scoreOfThird = { {2, 2, 1, 2}, {1, 1, 2, 2}, {1, 3, 3, 3},
                                {1, 3, 1, 3}, {3, 3, 2, 3}, {3, 3, 1, 1} };
        int[][] scoreOfFourth = { {2, 2, 3, 1}, {1, 1, 2, 1}, {3, 2, 2, 2},
                                {1, 1, 1, 3}, {1, 1, 2, 1}, {2, 2, 2, 2} };
        int[][] scoreOfFifth = { {1, 2, 2, 3}, {1, 1, 3, 1}, {2, 2, 1, 2},
                                {2, 3, 3, 3}, {1, 1, 1, 1}, {1, 1, 1, 1} };
        int[][] scoreOfSixth = { {1, 3, 1, 1}, {1, 1, 1, 3}, {1, 1, 1, 1}, {1, 1, 1, 1} };
        int[][] scoreOfSeventh = { {1, 1, 2, 1}, {2, 3, 1, 1}, {1, 1, 2, 2},
                                {3, 1, 3, 3}, {1, 2, 1, 1}, {2, 2, 1, 1} };

        for(int i = 0; i < scoreOfFirst[0].length; i++) {
            System.out.println(req.getFirst() + " " + (req.getFirst()-1));
            totalScore[i] += scoreOfFirst[req.getFirst()-1][i];
            totalScore[i] += scoreOfSecond[req.getSecond()-1][i];
            totalScore[i] += scoreOfThird[req.getThird()-1][i];
            totalScore[i] += scoreOfFourth[req.getFourth()-1][i];
            totalScore[i] += scoreOfFifth[req.getFifth()-1][i];
            totalScore[i] += scoreOfSeventh[req.getSeventh()-1][i];
        }

        for(int i = 0; i < scoreOfSixth[0].length; i++) {
            totalScore[i] += scoreOfSixth[req.getSixth()-1][i];
        }

        int resultIndex = 0;
        int max = -10;

        for(int i = 0; i < totalScore.length; i++) {
            if(max < totalScore[i]) {
                max = totalScore[i];
                resultIndex = i;
            }
        }

        switch(resultIndex) {
            case 0:
                return CoastType.BEOMSEOM;
            case 1:
                return CoastType.CHAGWIDO;
            case 2:
                return CoastType.IHOTEWOO;
            case 3:
                return CoastType.SEONGSAN;
        }

        return null;
    }

}
