package com.goormthon.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CoastResponse {
    private String dateType;

    private String coastalName;
    private String coastalType;
    private String coastalImage;
    private String coastalContent;

    private List<CreatureResponse> holdingCreature;

    private String location;
    private String locationImage;

    private List<PlaceResponse> place;
    private String friend;
    private String enemy;

    @Builder
    public CoastResponse(String dateType, String coastalName, String coastalType, String coastalImage, String coastalContent,
                         List<CreatureResponse> holdingCreature,
                         String location, String locationImage, List<PlaceResponse> place,
                         String friend, String enemy) {
        this.dateType = dateType;
        this.coastalName = coastalName;
        this.coastalType = coastalType;
        this.coastalImage = coastalImage;
        this.coastalContent = coastalContent;

        this.holdingCreature = holdingCreature;

        this.location = location;
        this.locationImage = locationImage;
        this.place = place;

        this.friend = friend;
        this.enemy = enemy;
    }


}
