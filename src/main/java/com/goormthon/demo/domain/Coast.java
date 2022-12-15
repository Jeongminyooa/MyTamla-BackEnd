package com.goormthon.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coast {

    @Id
    @GeneratedValue
    @Column(name = "coast_id")
    private Long id;

    @Column(name = "date_type")
    @Enumerated(EnumType.STRING)
    private DateType dateType;

    @Column(name = "coast_type")
    @Enumerated(EnumType.STRING)
    private CoastType coastType;

    @Column(name = "coastal_image")
    private String coastalImage;

    @Column(name = "coastal_content", length = 1000)
    private String coastalContent;

    private String location;

    @Column(name = "location_image")
    private String locationImage;

    @OneToMany(mappedBy = "coast", cascade = CascadeType.ALL)
    private List<HoldingCreature> creatures = new ArrayList<>();

    @OneToMany(mappedBy = "coast", cascade = CascadeType.ALL)
    private List<Place> places = new ArrayList<>();

    @Column(name = "friend")
    private String friend;

    @Column(name = "enemy")
    private String enemy;

    @Builder
    public Coast (DateType dateType, CoastType coastType, String coastalImage,
                  String coastalContent, String location, String locationImage) {
        this.dateType = dateType;
        this.coastType = coastType;
        this.coastalImage = coastalImage;
        this.coastalContent = coastalContent;
        this.location = location;
        this.locationImage = locationImage;
    }




}
