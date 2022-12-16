package com.goormthon.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {
    @Id
    @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coast_id")
    private Coast coast;

    private String image;

    private String url;

    private String location;
    private String name;
    private String time;

    @Builder
    public Place(Coast coast, String image, String url) {
        coast.getPlaces().add(this);
        this.image = image;
        this.url = url;
    }

}
