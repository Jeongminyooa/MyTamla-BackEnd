package com.goormthon.demo.domain;

public enum CreatureType {
    PROTECTION("보호종"),
    PERTURBATION("교란종");

    private String name;

    CreatureType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
