package com.goormthon.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HoldingCreature {
    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long creature_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coast_id")
    private Coast coast;

    private String image;

    private boolean active;

    @Builder
    public HoldingCreature(Coast coast, String image, boolean active) {
        coast.getCreatures().add(this);
        this.image = image;
        this.active = active;
    }
}
