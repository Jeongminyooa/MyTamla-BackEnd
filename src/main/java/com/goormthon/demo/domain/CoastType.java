package com.goormthon.demo.domain;

public enum CoastType {
    BEOMSEOM("범섬"),
    CHAGWIDO("차귀도"),
    IHOTEWOO("이호테우 해수욕장"),
    MARADO("마라도"),
    SEONGSAN("성산일출봉"),

    PYOSEON("표선 해수욕장"),
    JUNGMUN("중문색달 해수욕장"),
    BIYANGDO("비양도");

    private String name;

    CoastType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
