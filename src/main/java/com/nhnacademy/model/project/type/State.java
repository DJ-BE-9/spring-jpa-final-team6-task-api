package com.nhnacademy.model.project.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum State {
    ACTIVE,
    DORMANT, //휴면
    COMPLETED; //종료


    @JsonCreator
    public static State from(String value) {
        return State.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }
}
