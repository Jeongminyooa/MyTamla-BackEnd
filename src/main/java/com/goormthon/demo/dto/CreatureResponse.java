package com.goormthon.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreatureResponse {
    private String image;

    @Builder
    public CreatureResponse (String image){
        this.image = image;
    }
}
