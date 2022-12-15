package com.goormthon.demo.domain;

import com.goormthon.demo.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @Column(name = "coast_type")
    @Enumerated(EnumType.STRING)
    private CoastType coastType;

    @Builder
    public Record(CoastType coastType) {
        this.coastType = coastType;
    }
}
