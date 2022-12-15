package com.goormthon.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PlaceResponse {
    private String image;
    private String url;

    @Builder
    public PlaceResponse (String image, String url) {
        this.image = image;
        this.url = url;
    }
}
