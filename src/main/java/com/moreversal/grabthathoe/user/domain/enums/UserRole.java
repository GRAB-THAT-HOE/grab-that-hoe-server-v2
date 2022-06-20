package com.moreversal.grabthathoe.user.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {

    FARMER("FARMER"),
    WORKER("WORKER"),
    VOLUNTEER("VOLUNTEER");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @JsonCreator
    public static UserRole from(String s) {
        for(UserRole userRole : UserRole.values()) {
            if(userRole.getValue().equals(s)) {
                return userRole;
            }
        }

        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
