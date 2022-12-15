package com.goormthon.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreatureResponse {
    private String name;
    private String image;

    @Builder
    public CreatureResponse (String name, String image){
        this.name = name;
        this.image = image;
    }
}
