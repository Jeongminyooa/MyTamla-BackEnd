package com.goormthon.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PlaceResponse {
    private String image;
    private String url;
    private String name;
    private String time;
    private String location;

    @Builder
    public PlaceResponse (String image, String url, String name, String time, String location) {
        this.image = image;
        this.url = url;
        this.name = name;
        this.time = time;
        this.location = location;
    }
}
